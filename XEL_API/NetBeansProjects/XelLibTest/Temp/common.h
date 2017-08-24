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
#include <syslog.h>

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

#endif	/* COMMON_H */

