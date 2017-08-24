/* ============================================================================
 *
 * Copyright (c) 2009-2010 XeL Technology Inc., all rights reserved.
 * 
 * xel_app.h
 *
 * Description: some helpful functions
 * ========================================================================= */

#ifndef _XEL_APP_H_
#define _XEL_APP_H_

#ifdef __KERNEL__
#include <linux/string.h>
#include <linux/kernel.h>
#include <linux/module.h>
#define PRINT printk
#else
#include <stdio.h>
#include <string.h>
#define PRINT printf
#endif

#ifdef __cplusplus
extern "C" {
#endif
    
#define MAX_RECORD_BYTE (576>>3)

int xel_app_create_db(int dev_id, int db_id, int keysize, int bank, int table_id);
int xel_app_add_record(int dev_id, int db_id, int record_id, int opt_index, char *data, char *mask);
int xel_app_check_record(char *record_data, char *record_mask, char *data, char *mask, int keysize);
int xel_app_config_ltr(int dev_id, int ltr_id, int db0_id);
int xel_app_config_opt(int dev_id, int keysize, int opt_index, char *opt_ptr);

#ifdef __cplusplus
}
#endif

#endif /* _XEL_APP_H_ */
