/* 
 * File:   common.h
 * Author: dubel2
 *
 * Created on 20 Апрель 2011 г., 16:11
 */

#ifndef COMMON_H
#define	COMMON_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <string>
#include <iostream>
#include <sstream>
#include <pthread.h>
#include <arpa/inet.h>
#include <math.h>
#include <cstdlib>
#include <vector>
#include <iomanip>
#include <syslog.h>
#include <sys/cdefs.h>

using namespace std;

#define ASYNC_GIDRA_CTRL
#define MAX_MSG_SIZE 32768

#define SYS_LOG_ON
#define FPRINTF_ON

#ifdef SYS_LOG_ON
    #define SYS_LOG(...) (syslog(__VA_ARGS__))
#else
    #define SYS_LOG(...) (__VA_ARGS__)
#endif


#ifdef FPRINTF_ON
    #define FPRINTF(...) (fprintf(__VA_ARGS__))
#else
    #define FPRINTF(...) (__VA_ARGS__)
#endif


inline char* CopyString(char* source, int size = 0) {
    if (source == NULL)
        return NULL;

    char* dest_str = NULL;
    int str_len = 0;
    
    if(size == 0)
        str_len = strlen(source);
    else 
        str_len = size;

    if (str_len > 0) {
        dest_str = (char*) calloc(str_len + 1, sizeof(char));
        strncpy(dest_str, source, str_len);
        strncpy(dest_str + str_len, "\0", 1);
    }
    return dest_str;
}

inline char* CopyStringEx(char* source, int size = 0) {
    if (source == NULL)
        return NULL;

    char* dest_str = NULL;
    int str_len = 0;
    
    if(size == 0)
        str_len = strlen(source);
    else 
        str_len = size;

    if (str_len > 0) {
        dest_str = (char*) calloc(str_len + 2, sizeof(char));
        strncpy(dest_str, source, str_len);
        strncpy(dest_str + str_len, "\n\0", 2);
    }
    return dest_str;
}

inline string intToString(int value) {

    std::ostringstream sin;
    sin << value;
    std::string str_value = sin.str();
    sin.clear();

    return str_value;
}

inline string getIDFileName(string fileName, int pid, int lid) {

    int delimiter_pos = 0;
    delimiter_pos = fileName.find_last_of("/\\");
    string dstFileName = fileName;
    string sID = intToString(pid) + "_" + intToString(lid) + "_";
    dstFileName.insert(delimiter_pos + 1, sID);

    return dstFileName;
}

inline char* getCharIDFileName(string fileName, int pid, int lid) {

    int delimiter_pos = 0;
    delimiter_pos = fileName.find_last_of("/\\");
    string dstFileName = fileName;
    string sID = intToString(pid) + "_" + intToString(lid) + "_";
    dstFileName.insert(delimiter_pos + 1, sID);
    char* cDstFileName = (char*)malloc(dstFileName.length());
    memcpy(cDstFileName, (char*)dstFileName.c_str(), dstFileName.length());
    
    return cDstFileName;
}

inline char* getShortName(char* fileName) {
    char * pch;
    char * file;
    pch = strrchr(fileName, '/');
    int len = (pch-fileName) + 1;
    if (len > 0) {
        file = (char*) malloc(len);
        strncpy(file, pch, len);
    }
    return file;
}

inline char * get_ip_str(const struct sockaddr *sa, char *s, size_t maxlen) {
    switch (sa->sa_family) {
        case AF_INET:
            inet_ntop(AF_INET, &(((struct sockaddr_in *) sa)->sin_addr), s, maxlen);
            break;
        case AF_INET6:
            inet_ntop(AF_INET6, &(((struct sockaddr_in6 *) sa)->sin6_addr), s, maxlen);
            break;
        default:
            strncpy(s, "Unknown AF", maxlen);
            return NULL;
    }
    return s;
}

inline double round(double r) {
    return (r > 0.0) ? floor(r + 0.5) : ceil(r - 0.5);
}


inline byte* StringToBytes(const char* data, const char* delimiters, int base) {

    char* strVal = NULL;
    byte* nData = NULL;
    vector<byte> values;
    vector<byte>::iterator values_it;

    if (data != NULL) {
        strVal = strtok((char*)data, delimiters);

        while (strVal != NULL){
            byte tmp = (byte)strtoul(strVal, NULL, base);
            values.push_back(tmp);
            strVal = strtok(NULL, delimiters);
        }

        if (values.size() > 0) {
            nData = (byte*) malloc(values.size());
            memcpy(nData, &values[0], values.size());
            values.clear();
        }
    }
    return nData;
}


//inline char* BytesToString(byte* data, int size, const char* delimiters, int base) {
inline string BytesToString(byte* data, int size, const char* delimiters, int base) {

    std::ostringstream out;

    int delimitersLen = strlen(delimiters);
    for (int i = 0; i < size; i++) {

        if (base == 16) {
            out.fill('0');
            out << std::setw(2) << std::hex << std::uppercase;
        }
        
        out << static_cast<unsigned short> (*(data + i));

        if (i < size - 1 && delimitersLen > 0)
            out << *delimiters;
    }
    out << "\0";
    std::string str = out.str();

    out.clear();
    out.str("");

    return str;
    //return (char*)str.c_str();
}

inline char* BytesToCharString(byte* data, int size, const char* delimiters, int base) {

    std::ostringstream out;

    int delimitersLen = strlen(delimiters);
    for (int i = 0; i < size; i++) {

        if (base == 16) {
            out.fill('0');
            out << std::setw(2) << std::hex << std::uppercase;
        }
        
        out << static_cast<unsigned short> (*(data + i));

        if (i < size - 1 && delimitersLen > 0)
            out << *delimiters;
    }
    out << "\0";
    std::string str = out.str();

    out.clear();
    out.str("");

    char* ret = CopyString((char*)str.c_str());
    
    str = "";
    str.clear();
    
    return ret;
}


/*
 * Find the first occurrence of find in s, where the search is limited to the
 * first slen characters of s.
 */

inline char* strnstr(const char *s, const char *find, size_t slen) {
    char c, sc;
    size_t len;

    if ((c = *find++) != '\0') {
        len = strlen(find);
        do {
            do {
                if (slen-- < 1 || (sc = *s++) == '\0')
                    return (NULL);
            } while (sc != c);
            if (len > slen)
                return (NULL);
        } while (strncmp(s, find, len) != 0);
        s--;
    }
    return ((char *) s);
}

#endif	/* COMMON_H */

