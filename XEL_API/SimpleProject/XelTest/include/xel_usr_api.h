/**
  ****************************************************************
  *    copyright (c) 2009-2012   XeL Technology, Inc.
  *
  *
  *****************************************************************
  */


#ifndef __XEL_USR_API__
#define __XEL_USR_API__
#include "xel_version.h"

#define XEL_IN
#define XEL_OUT
#define XEL_INOUT

/*Defines for configuration*/
#define XEL_API_CFG_DEVICE_TOT          64 
#define XEL_API_CFG_BANK_TOT            2
#define XEL_API_CFG_TABLE_TOT           16
#define XEL_API_CFG_SIZE_TYPE_TOT       5
#define XEL_API_CFG_DATABASE_TOT        64

#define XEL_API_CFG_LTR_TOT             32

#define XEL_API_CFG_OPT_TOT             16

#define XEL_API_CFG_STRIPE_TOT          (576/16)

#define XEL_API_CFG_SIZE_72             72
#define XEL_API_CFG_SIZE_144            144
#define XEL_API_CFG_SIZE_288            288
#define XEL_API_CFG_SIZE_432            432
#define XEL_API_CFG_SIZE_576            576

#define XEL_API_CFG_PRI_TOT             0x3FFFD

#define XEL_API_CFG_EPRI_72_TOT         0x3FFFD


#define XEL_API_CFG_VOID_DATABASE       0xFFFFFFFF
#define XEL_API_CFG_VOID_DEFAULT_INDEX 	0xFFFFF

#define XEL_API_CFG_RECORD_ID_TOT	(1 << 18) - 2

#define XEL_CHANNEL_ID_MASK             0x01f
#define XEL_CHANNEL_ID_OFF              0x02
#define get_channel_id(dev_id)   ((dev_id >> XEL_CHANNEL_ID_OFF) & XEL_CHANNEL_ID_MASK)

#define XEL_ERR_DETECT_FOUND            1
#define XEL_ERR_DETECT_NOT_FOUND        2
#define XEL_ERR_TYPE_VOID               0xff

enum
{
    XEL_OK = 0,
    XEL_ERR = -1, 
    
    XEL_ERR_NO_MEM     = -100,
    XEL_ERR_NO_CAM     = -101,
    XEL_ERR_DB_NO_CAM  = -109,
    
    XEL_API_ERR_PARAM         = -2101,
    XEL_API_ERR_MEM           = -2102,
    XEL_API_ERR_DB            = -2103, /* no such database */
    XEL_API_ERR_DB_SIZE       = -2104, /* over the limit database size */
    XEL_API_ERR_DB_MODE       = -2105, /* database mode check error */    
    XEL_API_ERR_RECORD_ID     = -2106, /* record id is invalid, for example id > 0x3fffd when supercam */
    XEL_API_ERR_OVER_PAYSIZE  = -2107, /* over paysize */
    XEL_API_ERR_LINK_SIZE     = -2108, /* link entry error size, only support 72bit 144bit 288bit */
    XEL_API_ERR_LINK_OPT      = -2109, /* no GE opt, or has LT opt for link entry */
    XEL_API_ERR_LINK_HIGHER   = -2110, /* data is not equal for record and higher in link entry  */
};

/*block register address*/
#define XEL_BLK_REG_BESR0            0x002
#define XEL_BLK_REG_BESR1            0x102
#define XEL_BLK_REG_BESR2            0x202
#define XEL_BLK_REG_BESR3            0x302

/*chip register address*/
typedef enum
{
    XEL_REG_REV =  0x00 ,
	XEL_REG_PS = 0x01   ,
    XEL_REG_ID          ,
    XEL_REG_CCR         ,   
    XEL_REG_RPID0       ,  
    XEL_REG_RPID1       ,
    XEL_REG_RPID2       ,
    XEL_REG_RPID3       ,
    XEL_REG_HRR         ,
    XEL_REG_LRR         ,
    XEL_REG_MBIST_CTRL = 0x1f,    
    XEL_REG_SPR = 0x30  ,
    XEL_REG_MAUE = 0x35 ,
    XEL_REG_STAT = 0x36 ,
    XEL_REG_MSR = 0x37  ,
    XEL_REG_MER = 0x38  ,
	XEL_REG_SIZE = 0x3c ,
    XEL_REG_LTR0 = 0x40 ,
    XEL_REG_LTR1 = 0x41 ,
    XEL_REG_LTR2        ,
    XEL_REG_LTR3        ,
    XEL_REG_LTR4        ,
    XEL_REG_LTR5        ,
    XEL_REG_LTR6        ,
    XEL_REG_LTR7        ,
    XEL_REG_LTR8        ,
    XEL_REG_LTR9        ,
    XEL_REG_LTR10       ,
    XEL_REG_LTR11       ,
    XEL_REG_LTR12       ,
    XEL_REG_LTR13       ,
    XEL_REG_LTR14       ,
    XEL_REG_LTR15       ,
    XEL_REG_LTR16       ,
    XEL_REG_LTR17       ,
    XEL_REG_LTR18       ,
    XEL_REG_LTR19       ,
    XEL_REG_LTR20       ,
    XEL_REG_LTR21       ,
    XEL_REG_LTR22       ,
    XEL_REG_LTR23       ,
    XEL_REG_LTR24       ,
    XEL_REG_LTR25       ,
    XEL_REG_LTR26       ,
    XEL_REG_LTR27       ,
    XEL_REG_LTR28       ,
    XEL_REG_LTR29       ,   
    XEL_REG_LTR30       ,
    XEL_REG_LTR31       
}xel_chip_reg_t;

#ifndef xel_uint_t
typedef unsigned int   xel_uint_t;
#endif

#ifndef xel_uchar_t
typedef unsigned char  xel_uchar_t;
#endif

#ifndef xel_void_t
typedef void           xel_void_t;
#endif

#ifndef xel_status_t
typedef int            xel_status_t;
#endif

#ifndef xel_bool_t
typedef enum
{
   XEL_TRUE  = 1,
   XEL_FALSE = 0,
} xel_bool_t;
#endif




typedef struct  
{
   xel_uint_t			database_count;
   xel_uint_t           record_count;   	
   xel_uint_t			record_size_count[XEL_API_CFG_SIZE_TYPE_TOT];  
   xel_uint_t			record_table_count[XEL_API_CFG_TABLE_TOT];
   xel_uint_t			record_bank_count[XEL_API_CFG_BANK_TOT];
} xel_info_chain_t; 


typedef enum
{
    XEL_API_UPDATE_ID = 0,
	XEL_API_UPDATE_PRIORITY = 1,
	XEL_API_UPDATE_ACTION0 = 2,
} xel_record_update_t;

typedef enum
{
    XEL_API_MODE_SUPERCAM = 0,
	XEL_API_MODE_SUPERXLE,
	XEL_API_MODE_ACTION,
	XEL_API_MODE_TOT,
} xel_database_mode_t;

typedef struct xel_api_database
{
   xel_uint_t          database_id  ; 
   xel_uint_t          table_id     ; 
   xel_uint_t          record_size  ; 
   xel_uint_t          bank_id      ; 
   xel_database_mode_t database_mode;
   xel_uint_t          database_size; /* max count of records */
   xel_uint_t          fib;
   xel_uint_t          share_group; /* (1~32), 0 means no shared group, others are invalid */

    xel_uint_t          mux_priority; /* (0~1) */
    xel_uint_t          def_index;
} xel_api_database_t;


typedef struct  
{
   xel_uint_t           record_count;   // Number of records in the database
   xel_api_database_t   database_config; 
} xel_info_database_t; 



/* ltr config */
typedef struct 
{
        xel_uint_t  ltr_id;        
        xel_uint_t  database_id0;  
} xel_api_ltr_t;


/* opt type config */
/*
 * Group and stripe opt
 */
typedef enum
{
    XEL_API_OPT_TYPE,
    XEL_API_OPT_TYPE_EQ,
    XEL_API_OPT_TYPE_LT,
    XEL_API_OPT_TYPE_GE,
}
xel_stripe_type_t;

typedef struct
{
   xel_uint_t  record_size;
   xel_uchar_t opt_index;
   xel_uchar_t stripe_type[XEL_API_CFG_STRIPE_TOT];
} xel_api_opt_type_t;


/* entry */

typedef struct xel_api_record
{
    xel_uchar_t  *record_mask_ptr;    
    xel_uchar_t  *record_data_ptr;
    xel_uint_t   record_database;
    xel_uchar_t  record_opt;
    xel_uint_t   record_id;   
    xel_uint_t   record_priority;
	xel_uint_t	 record_action0;
   xel_uint_t	reserved;
} xel_api_record_t;



/* search */

typedef struct  /* Search key content */
{
   xel_uint_t        size;			/* search key size */
   xel_uchar_t    * key_ptr;		/* search key content */
} xel_api_search_key_t;



typedef struct  /*Returns search result */
{
   xel_uint_t     result0;
} xel_api_result_t; 



/*function for device api.*/

typedef struct xel_chip_conf
{
    unsigned char read_result_bus; /* 0 means default from data bus, 1 means from result bus */
    unsigned char clock_r_delay;
    unsigned char clock_r_invert;
    unsigned char io_compensation;
    unsigned char database_fixed;
    unsigned char init_allmem;
} xel_chip_conf_t;

xel_status_t
xel_api_init(XEL_IN xel_uint_t dev_id, XEL_IN xel_chip_conf_t *conf);


xel_status_t
xel_api_info_devs(XEL_OUT xel_uint_t *num_chains);

xel_status_t
xel_api_info_devs_chain(XEL_IN xel_uint_t dev_id, XEL_OUT xel_info_chain_t *info);

xel_status_t
xel_api_destroy(XEL_IN xel_uint_t   dev_id);

xel_status_t  
xel_api_share_group_create(int dev_id, int share_id, int share_size, int fib);

xel_status_t  
xel_api_share_group_remove(XEL_IN xel_uint_t dev_id,
                           XEL_IN xel_uint_t share_id);

xel_status_t  
xel_api_database_creat(XEL_IN xel_uint_t  dev_id,
                            XEL_IN xel_uint_t  database_id,
                            XEL_IN xel_api_database_t  *database_config_p);

xel_status_t  
xel_api_database_purge_entries(XEL_IN xel_uint_t  dev_id,
                                      XEL_IN xel_uint_t  database_id);

xel_status_t  
xel_api_database_remove(XEL_IN xel_uint_t  dev_id,
                              XEL_IN xel_uint_t  database_id);
xel_status_t
xel_api_info_database(XEL_IN xel_uint_t dev_id,XEL_IN xel_uint_t database_id,
                      XEL_OUT xel_info_database_t  *database_desc_p);

xel_status_t
xel_api_database_ltr_config(XEL_IN xel_uint_t dev_id, 
                                 XEL_IN xel_api_ltr_t *ltr_p);

xel_status_t
xel_api_info_ltr(XEL_IN xel_uint_t dev_id, 
                        XEL_IN xel_uint_t ltr_id, XEL_OUT xel_api_ltr_t *ltr_p);

xel_status_t
xel_api_adjust_delay(XEL_IN xel_uint_t dev_id,
                     XEL_IN xel_uchar_t clock_r_delay,
                     XEL_IN xel_uchar_t clock_r_invert);

xel_status_t
xel_api_ltr_key_shift(XEL_IN xel_uint_t dev_id, 
						XEL_IN xel_uint_t  ltr_id, XEL_IN xel_bool_t shift);
unsigned int
xel_api_info_ltr_key_shift(XEL_IN xel_uint_t dev_id, XEL_IN xel_uint_t ltr_id);


xel_status_t
xel_api_record_opt_type_config(XEL_IN xel_uint_t dev_id,
                                XEL_IN xel_api_opt_type_t *opt_p);

xel_status_t
xel_api_info_opt_type(XEL_IN xel_uint_t dev_id, XEL_INOUT xel_api_opt_type_t *opt_p); 

xel_status_t
xel_api_add_range_record(XEL_IN xel_uint_t dev_id, XEL_IN xel_uchar_t *higher_data, 
                         XEL_INOUT  xel_api_record_t *record_p);

xel_status_t
xel_api_add_record(XEL_IN xel_uint_t dev_id, 
                       XEL_INOUT  xel_api_record_t *record_p);

xel_status_t
xel_api_del_record(XEL_IN xel_uint_t dev_id,
                              XEL_IN xel_uint_t database_id,
                              XEL_IN xel_uint_t record_id);


xel_status_t
xel_api_read_record(XEL_IN xel_uint_t dev_id, 
                        XEL_IN xel_uint_t database_id,
                        XEL_IN xel_uint_t record_id,
                        XEL_OUT  xel_api_record_t *record_p);

xel_status_t
xel_api_read_range_record(XEL_IN xel_uint_t dev_id, 
                          XEL_IN xel_uint_t database_id,
                          XEL_IN xel_uint_t record_id,
                          XEL_OUT xel_uchar_t *higher_data, 
                          XEL_OUT xel_api_record_t *record_p);

xel_status_t
xel_api_update_record(XEL_IN xel_uint_t dev_id, 
                        XEL_IN xel_uint_t database_id,
                        XEL_IN xel_uint_t record_id,
                        XEL_IN xel_record_update_t flag, 
                        XEL_IN xel_uint_t data);

/*search op.*/
xel_status_t
xel_api_search(XEL_IN xel_uint_t channel_id,
		   XEL_IN xel_uint_t ltr_id,
                   XEL_IN xel_api_search_key_t *key_p,
                   XEL_OUT xel_api_result_t *result_p);

/*chip register debug function*/
xel_status_t
xel_api_chip_reg_read(XEL_IN xel_uint_t dev_id,
                           XEL_IN xel_uint_t reg_addr,
                           XEL_OUT xel_uchar_t *reg_data);

xel_status_t
xel_api_chip_reg_write(XEL_IN xel_uint_t dev_id,
                           XEL_IN xel_uint_t reg_addr,
                           XEL_IN xel_uchar_t *reg_data);

/*block register debug function*/
xel_status_t  
xel_api_block_reg_read(XEL_IN xel_uint_t dev_id,
                            XEL_IN xel_uint_t block,
                            XEL_IN xel_uint_t reg_addr,
                            XEL_OUT xel_uchar_t *reg_data);

xel_status_t xel_api_scratch_read(xel_uint_t dev_id, xel_uchar_t *data);
xel_status_t xel_api_scratch_write(xel_uint_t dev_id, xel_uchar_t *data);

/***********************************************************************
backup data of destinated device to the given file 
************************************************************************/
xel_status_t
xel_api_raw_data_backup_dev (XEL_IN xel_uint_t dev_id, XEL_IN xel_uchar_t *file_name);


/***********************************************************************
recover data of the destinated device from the given file
************************************************************************/
xel_status_t 
xel_api_raw_recover_dev_from_file (XEL_IN xel_uint_t dev_id, XEL_IN xel_uchar_t *file_name);

xel_status_t xel_api_mbist_test(XEL_IN xel_uint_t dev_id);


/***********************************************************************
  api interface of error detection function
************************************************************************/
xel_status_t
xel_api_mem_scan_end(xel_uint_t dev_id);

xel_status_t
xel_api_mem_scan_start(xel_uint_t dev_id, xel_uint_t mem_op, xel_uint_t reg_op);

xel_status_t
xel_api_error_detect_repair(XEL_IN int fix_flag, 
							XEL_IN int dev_id, 
							XEL_INOUT int *db_id, 
							XEL_INOUT int *record_id, 
							XEL_INOUT int *err_type,
							XEL_INOUT int *find);

#endif /* __XEL_USR_API__ */
