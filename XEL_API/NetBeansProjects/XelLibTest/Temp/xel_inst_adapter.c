/************************************************************************
Copyright     :  2009-2013,  XeL Technology, Inc.
FileName      :  Xel_inst_adapter.c
Description   :  ise instruction adapter reference
Auto          :  
Version       :  1.0
Date          :  
History       :  

*************************************************************************/
#ifndef __KERNEL__
#include <stdio.h>
#include <string.h>
#define PRINT printf
#include "common.h"
#else
#include <linux/string.h>
#define PRINT printk
#endif


#define CHUNCK_SIZE_IN_BYTES    12



/*read ins*/
#define  XEL_INS_RDR  0x40
#define  XEL_INS_RDD  0x41
#define  XEL_INS_RDB  0x42
/*write ins*/
#define  XEL_INS_WRD  0x96
#define  XEL_INS_WRR  0x93
#define  XEL_INS_DEL  0x94
#define  XEL_INS_FWR  0x98

/*search ins*/
#define  XEL_INS_WKR0     0x80
#define  XEL_INS_WKR(n)   (0x80+n)
#define  XEL_INS_WKR_MAX  8

#define  XEL_INS_WS0      0xC0
#define  XEL_INS_WS1      0xC8
#define  XEL_INS_WS2      0xD0
#define  XEL_INS_WS3      0xD8
#define  XEL_INS_WS4      0xE0
#define  XEL_INS_WS5      0xE8
#define  XEL_INS_WS6      0xF0
#define  XEL_INS_WS7      0xF8
#define  XEL_INS_WS8      0xC4
#define  XEL_INS_WS9      0xCC
#define  XEL_INS_WS10      0xD4
#define  XEL_INS_WS11      0xDC
#define  XEL_INS_WS12      0xE4
#define  XEL_INS_WS13      0xEC
#define  XEL_INS_WS14      0xF4
#define  XEL_INS_WS15      0xFC
#define  XEL_INS_WS16      0xC2
#define  XEL_INS_WS17      0xCA
#define  XEL_INS_WS18      0xD2
#define  XEL_INS_WS19      0xDA
#define  XEL_INS_WS20      0xE2
#define  XEL_INS_WS21      0xEA
#define  XEL_INS_WS22      0xF2
#define  XEL_INS_WS23      0xFA
#define  XEL_INS_WS24      0xC6
#define  XEL_INS_WS25      0xCE
#define  XEL_INS_WS26      0xD6
#define  XEL_INS_WS27      0xDE
#define  XEL_INS_WS28      0xE6
#define  XEL_INS_WS29      0xEE
#define  XEL_INS_WS30      0xF6
#define  XEL_INS_WS31      0xFE

/*set search result*/
#define RESULT_MATCH0_BYTE_OFF      3
#define RESULT_MATCH1_BYTE_OFF      7
#define RESULT_MATCH_BIT_OFF        7

#define xel_set_result_match0(x, v)     (x[RESULT_MATCH0_BYTE_OFF] = (x[RESULT_MATCH0_BYTE_OFF] | ((v<<RESULT_MATCH_BIT_OFF)&0x80)))
#define xel_set_result_match1(x, v)     (x[RESULT_MATCH1_BYTE_OFF] = (x[RESULT_MATCH1_BYTE_OFF] | ((v<<RESULT_MATCH_BIT_OFF)&0x80)))

#if 0
/*
*   ��ʾ��������:
    ����㲻����SDK��ݹ�������ݣ��͵�ַ��Ϣ�ȣ�����ʾ���е�˳���pucParamsArray����ݴ��ݸ�FPGA/AISC��FIFO��
    FPGA/ASIC���uiOpcodeʹ����Ӧ��ʱ���FIFO�е���ݷ��͵�databus�ϡ�
*   This example be applicable to:
    Instruction adatapter layer do not care about where is address and where is data in pucParamsArray. 
    But only send data in pucParamsArray as sequence in the example to FPGA/ASIC FIFO.
    Then FPGA/ASIC send uiOpcode to instruction bus, and send data in the FIFO to data bus.
*/
/******************************union implemention*******************************/


int user_write_9_byte_data_to_data_fifo(unsigned int uiChannelId, unsigned char *data)
{
    //write low 9 byte of the input data to fifo
    
    return 0;
}

int user_start_instruction(unsigned int uiChannelId,
                               unsigned int uiOpcode,
                               unsigned int uiNopsBefore,
                               unsigned int uiNopsAfter)
{
    //send uiOpcode to instruction bus and send data of fifo to data bus 
    
    return 0;
}


int user_get_ready_flag(unsigned int  uiChannelId, unsigned int *flag)
{
    //get ready flag from fpga/asic/NP
    return 0;
}

int user_get_result(unsigned int  uiChannelId, unsigned char *OutData)
{
    //get result data
    return 0;
}


int XEL_InstAdaptCommand(unsigned int uiChannelId,
                                  unsigned int uiOpcode,
                                  unsigned int uiNopsBefore,
                                  unsigned int uiNopsAfter,
                                  int bWaitforDataReady,
                                  unsigned int uiParamsSize,
                                  unsigned char *pucParamsArray,
                                  unsigned char *pucOutData)
{
    int              iInd = 0;
    int              retVal = 0;
    unsigned int     flag = 0;
    unsigned int     uiRetriesCount = 0, uiRetriesNumber = 0;
    unsigned char    *pucCurChunk = NULL;
    			
    //PRINT("XEL_InstAdaptCommand start:\r\n");

    /* Validate parameters */
    if( uiOpcode >= ( 1 << 8 ) )
    {
        PRINT("XEL_InstAdaptCommand: Illegal opcode\r\n");
        return -1;
    }

    if( uiParamsSize > 8 )
    {
        PRINT("XEL_InstAdaptCommand:  Illegal params size\r\n");
        return -1;
    }

    /* Write the data from MSB chunk to data fifo*/
    for( iInd = uiParamsSize - 1; iInd >= 0; iInd-- )
    {
        pucCurChunk = pucParamsArray + iInd * CHUNCK_SIZE_IN_BYTES;
        retVal = user_write_9_byte_data_to_data_fifo(uiChannelId, pucCurChunk);
        if(retVal)
        {
            PRINT("user_write_9_byte_data_to_data_fifo error\r\n");
            return(-1);
        }
    }

   /* Write the instruction and data to the device */
    retVal = user_start_instruction(uiChannelId, uiOpcode, uiNopsBefore, uiNopsAfter);
    if(retVal)
    {
        PRINT("user_start_instruction error\r\n");
        return(-1);
    }


   if( bWaitforDataReady )
   {
      /* Read data if needed (Read\search command) */
      /* Wait for data to be ready, no need to wait for Ack it comes before the valid. */
      uiRetriesNumber = 100; //user decide
      uiRetriesCount = 0;
      do
      {
         if ( uiRetriesCount++ > uiRetriesNumber )
         {
            PRINT("XEL_InstAdaptCommand: failed to get ready bit.\n" );
            return retVal;
         }
         user_get_ready_flag( uiChannelId, &flag);
      }
      while (!flag);

      /* Read the data */
      user_get_result(uiChannelId, pucOutData);
   }

   return 0;
}	
#else
/* 
*   ��ʾ��������:
*   �û��ѵ�ַ����ݴ�SDK��ݵ�pucParamsArray�н���������Ȼ�����Լ�����Ҫ���ݸ�FPGA/ASIC��
*   This example be applicable to:
    Instruction adatapter layer get address and data from pucParamsArray. Then send uiOpcode,address,data
    to FPGA/ASIC. And then FPGA/ASIC send uiOpcode to instruction bus, send address and data to data bus.
*/
int user_send_search_instruction(unsigned int uiChannelId,
                                        unsigned int uiOpcode,
                                        unsigned char *key)
{
    SYS_LOG(LOG_DEBUG, "user_send_search_instruction uiChannelId: 0x%x, uiOpcode: 0x%0", uiChannelId, uiOpcode);
    
    //user need finish:
    /*
    send uiOpcode to instruction bus,
    send key to data bus 

    then check if instruction be excuted successfull
    */
    
    return 0;
}

int user_get_search_result(unsigned int uiChannelId,
                                unsigned char *pucOutData)
{

    SYS_LOG(LOG_DEBUG, "user_get_search_result uiChannelId: 0x%x", uiChannelId);
    
    unsigned int tmp_data0, tmp_data1;
    unsigned int hit_flag0, hit_flag1;

    if(NULL == pucOutData)
    {
        PRINT("user_get_search_result error: pucOutData is null\r\n");
        return(-1);
    }
    memset(pucOutData, 0 ,9);
    tmp_data0 = 0;
    hit_flag0 = 0;
    tmp_data1 = 0;
    hit_flag1 = 0;

    
    
    //user need finish:
    /*  hit_flag0 = hit bit from ise result bus
        tmp_data0 = result  from ise result bus
    */
    if(hit_flag0)
    {
        *(pucOutData + 0) = tmp_data0         & 0xff;
        *(pucOutData + 1) = (tmp_data0 >> 8 ) & 0xff;
        *(pucOutData + 2) = (tmp_data0 >> 16) & 0xff;
        xel_set_result_match0(pucOutData, 1);
    }
    else
    {
        xel_set_result_match0(pucOutData, 0);
    }

    //for parallel search, need set result 1
    //user need finish:
    /*  hit_flag1 = hit bit from ise result bus
        tmp_data1 = result  from ise result bus
    */    
    if(hit_flag1)
    {
        *(pucOutData + 4) = tmp_data0         & 0xff;
        *(pucOutData + 5) = (tmp_data0 >> 8 ) & 0xff;
        *(pucOutData + 6) = (tmp_data0 >> 16) & 0xff;
        xel_set_result_match1(pucOutData, 1);
    }
    else
    {
        xel_set_result_match1(pucOutData, 0);
    }
    
    return 0;
}


int user_search(unsigned int uiChannelId,
                   unsigned int uiOpcode,
                   unsigned int uiParamsSize,
                   unsigned char *pucParamsArray,
                   unsigned char *pucOutData)
{
    unsigned char *buf_key;
    buf_key = NULL;
    
    SYS_LOG(LOG_DEBUG, "user_search uiChannelId: 0x%x; uiOpcode: 0x%x; uiParamsSize: 0x%x", uiChannelId, uiOpcode, uiParamsSize);
    
    #if 1//finish the instruction all by hardware
        //user need finish:
        /*  send uiOpcode and pucParamsArray to FPGA/ASIC/NP
            then FPGA/ASIC/NP finish the instruction like code in #else
        */    
        SYS_LOG(LOG_DEBUG, "user_search: #if 1 send uiOpcode and pucParamsArray to FPGA/ASIC/NP then FPGA/ASIC/NP finish the instruction like code in #else");
    #else 
        SYS_LOG(LOG_DEBUG, "user_search: else");
        
       //finish the instruction partly by software
        
    switch(uiParamsSize)
    {
        case 8:
            buf_key = pucParamsArray + CHUNCK_SIZE_IN_BYTES*7;
            user_send_search_instruction(uiChannelId, XEL_INS_WKR(7), buf_key);
        case 7:
            buf_key = pucParamsArray + CHUNCK_SIZE_IN_BYTES*6;
            user_send_search_instruction(uiChannelId, XEL_INS_WKR(6), buf_key);
        case 6:
            buf_key = pucParamsArray + CHUNCK_SIZE_IN_BYTES*5;
            user_send_search_instruction(uiChannelId, XEL_INS_WKR(5), buf_key);
        case 5:
            buf_key = pucParamsArray + CHUNCK_SIZE_IN_BYTES*4;
            user_send_search_instruction(uiChannelId, XEL_INS_WKR(4), buf_key);
        case 4:
            buf_key = pucParamsArray + CHUNCK_SIZE_IN_BYTES*3;
            user_send_search_instruction(uiChannelId, XEL_INS_WKR(3), buf_key);
        case 3:
            buf_key = pucParamsArray + CHUNCK_SIZE_IN_BYTES*2;
            user_send_search_instruction(uiChannelId, XEL_INS_WKR(2), buf_key);
        case 2:
            buf_key = pucParamsArray + CHUNCK_SIZE_IN_BYTES*1;
            user_send_search_instruction(uiChannelId, XEL_INS_WKR(1), buf_key);
        case 1:
            buf_key = pucParamsArray;
            user_send_search_instruction(uiChannelId, uiOpcode, buf_key);
            user_get_search_result(uiChannelId, pucOutData);
            break;
        default:
            PRINT("user_search: uiParamsSize(%d) is error\r\n", uiParamsSize);
            return(-1);
    }
    #endif
    
    return(0);
}


int XEL_InstAdaptCommand(unsigned int uiChannelId,
                         unsigned int uiOpcode,
                         unsigned int uiNopsBefore,
                         unsigned int uiNopsAfter,
                         int bWaitforDataReady,
                         unsigned int uiParamsSize,
                         unsigned char *pucParamsArray,
                         unsigned char *pucOutData)
{
    unsigned char * buf_addr   = NULL;
    unsigned char * buf_slice0 = NULL;
    unsigned char * buf_slice1 = NULL;
    unsigned char * buf_data   = NULL;
    

SYS_LOG(LOG_DEBUG, "XEL_InstAdaptCommand:  uiChannelId: 0x%x, uiOpcode: 0x%x, uiNopsBefore: 0x%x, uiNopsAfter: 0x%x, bWaitforDataReady: 0x%x, uiParamsSize: 0x%x", 
        uiChannelId, uiOpcode, uiNopsBefore, uiNopsAfter, bWaitforDataReady, uiParamsSize);

	switch(uiOpcode)
	{
        case XEL_INS_WRR:
        {
            SYS_LOG(LOG_DEBUG, "XEL_InstAdaptCommand:  XEL_INS_WRR:");
    	    
            buf_addr = pucParamsArray+CHUNCK_SIZE_IN_BYTES;
            buf_data = pucParamsArray;
            
            //user need finish:
            /*
            send uiOpcode to instruction bus,
            send buf_addr and buf_data to data bus 

            then check if instruction be excuted successfull
            */
        }
        break;

        case XEL_INS_WRD:
        {	
            SYS_LOG(LOG_DEBUG, "XEL_InstAdaptCommand:  XEL_INS_WRD:");
            
    	    buf_addr   = pucParamsArray + CHUNCK_SIZE_IN_BYTES*2;
            buf_slice0 = pucParamsArray + CHUNCK_SIZE_IN_BYTES;
            buf_slice1 = pucParamsArray;
            //user need finish:
            /*
            send uiOpcode to instruction bus,
            send buf_addr, buf_slice0 and buf_slice1 to data bus 

            then check if instruction be excuted successfull
            */            
        }
        break;
        case XEL_INS_FWR:
        {			
            SYS_LOG(LOG_DEBUG, "XEL_InstAdaptCommand:  XEL_INS_FWR:");
            
    	    buf_addr   = pucParamsArray + CHUNCK_SIZE_IN_BYTES*3;
            buf_slice0 = pucParamsArray + CHUNCK_SIZE_IN_BYTES*2;
            buf_slice1 = pucParamsArray + CHUNCK_SIZE_IN_BYTES;
            buf_data   = pucParamsArray;
            //user need finish:
            /*
            send uiOpcode to instruction bus,
            send buf_addr, buf_slice0, buf_slice1 and buf_data to data bus 

            then check if instruction be excuted successfull
            */            
        }
        break;
            
        case XEL_INS_RDR:
        {
            SYS_LOG(LOG_DEBUG, "XEL_InstAdaptCommand:  XEL_INS_RDR:");
    	    buf_addr = pucParamsArray;
            //user need finish:
            /*
            send uiOpcode to instruction bus,
            send buf_addr to data bus 

            then get read data from data bus
            */
        }
        break;
        case XEL_INS_RDD:
        {
            SYS_LOG(LOG_DEBUG, "XEL_InstAdaptCommand:  XEL_INS_RDD:");
    	    buf_addr = pucParamsArray;
            //user need finish:
            /*
            send uiOpcode to instruction bus,
            send buf_addr to data bus 

            then get read data from data bus
            */
        }
        break;
        case XEL_INS_RDB:
        {
            SYS_LOG(LOG_DEBUG, "XEL_InstAdaptCommand:  XEL_INS_RDB:");
    	    buf_addr = pucParamsArray;
            //user need finish:
            /*
            send uiOpcode to instruction bus,
            send buf_addr to data bus 

            then get read data from data bus
            */
        }
        break;
            
        case XEL_INS_DEL:
        {
            SYS_LOG(LOG_DEBUG, "XEL_InstAdaptCommand:  XEL_INS_DEL:");
    	    buf_addr = pucParamsArray;
            //user need finish:
            /*
            send uiOpcode to instruction bus,
            send buf_addr to data bus 

            then check if instruction be excuted successfull
            */
        }
        break;

    default:
        if((XEL_INS_WS0 <= uiOpcode) && (XEL_INS_WS31 >= uiOpcode))
        {
            SYS_LOG(LOG_DEBUG, "XEL_InstAdaptCommand:  (XEL_INS_WS0 <= uiOpcode) && (XEL_INS_WS31 >= uiOpcode)");
            user_search(uiChannelId, uiOpcode, uiParamsSize, pucParamsArray, pucOutData);
        }
        else
        {
            SYS_LOG(LOG_DEBUG, "XEL_InstAdaptCommand:  uiOpcode(0x%x) error", uiOpcode);
            PRINT("uiOpcode(0x%x) error\r\n", uiOpcode);
            return(-1);
        }
        break;
	}

        SYS_LOG(LOG_DEBUG, "XEL_InstAdaptCommand:  exit", uiOpcode);
	return 0;
}	



#endif

