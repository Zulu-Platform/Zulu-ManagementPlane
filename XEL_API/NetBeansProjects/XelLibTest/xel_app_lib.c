/* ============================================================================
 *
 * Copyright (c) 2009-2010 XeL Technology Inc., all rights reserved.
 * 
 * xel_app_lib.c
 *
 * Description: some helpful functions
 * ========================================================================= */

#include "xel_usr_api.h"
#include "xel_app.h"

int xel_app_create_db(int dev_id, int db_id, int keysize, int bank, int table_id)
{
    xel_api_database_t db_cfg;
    
    memset(&db_cfg, 0, sizeof(db_cfg));
    db_cfg.database_size = 8096; /* 8K entries for example */
    db_cfg.database_id = db_id;
    db_cfg.record_size = keysize;
    db_cfg.bank_id = bank;
    db_cfg.table_id = table_id;

    db_cfg.def_index = XEL_API_CFG_VOID_DEFAULT_INDEX;

    return xel_api_database_creat(dev_id, db_id, &db_cfg);
}

int xel_app_add_record(int dev_id, int db_id, int record_id, int opt_index, char *data, char *mask)
{
	xel_api_record_t test_record;

    memset(&test_record, 0, sizeof(test_record));
    test_record.record_database = db_id;
	test_record.record_id = record_id;
	test_record.record_opt = opt_index;
    test_record.record_data_ptr = (unsigned char *)data;
    test_record.record_mask_ptr = (unsigned char *)mask;    
	return xel_api_add_record(dev_id, (xel_api_record_t*)(&test_record));
}

static void print_data(unsigned char *data, int len)
{
    int i = 0;
    for (i = 0;i < len;i++)
    {
        PRINT("%02X ", data[i]);
    }
    PRINT("\n");
}

int xel_app_check_record(char *record_data, char *record_mask, char *data, char *mask, int size)
{
    int i = 0;
    if (data == NULL || mask == NULL || record_data == NULL || record_mask == NULL)
    {
        return 0;
    }
    
    if (memcmp(record_mask, mask, size) != 0)
    {
        PRINT("Wrong, mask is not equal\n");
        PRINT("record_mask\n");
        print_data((unsigned char *)record_mask, size);
        PRINT("mask\n");
        print_data((unsigned char *)mask, size);        
        return -1;
    }
    for (i = 0;i < size;i++)
    {
        if ((record_data[i] & (~mask[i])) != (data[i] & (~mask[i])))
        {
            PRINT("Wrong, data is not equal\n");
            return -1;
        }
    }
    return 0;
}


int xel_app_config_ltr(int dev_id, int ltr_id, int db0_id)
{
	xel_api_ltr_t ltr_cfg;

    memset(&ltr_cfg, 0, sizeof(ltr_cfg));
	ltr_cfg.ltr_id = ltr_id;
	ltr_cfg.database_id0  = db0_id;
	return xel_api_database_ltr_config(dev_id, &ltr_cfg);
}

int xel_app_config_opt(int dev_id, int keysize, int opt_index, char *opt_ptr)
{
	xel_api_opt_type_t opt_cfg;
    
    memset(&opt_cfg, 0, sizeof(opt_cfg));
    opt_cfg.record_size = keysize;
    opt_cfg.opt_index = opt_index;
    if (opt_ptr == NULL)
    {
        memset(&opt_cfg.stripe_type, XEL_API_OPT_TYPE_EQ, XEL_API_CFG_STRIPE_TOT * sizeof(xel_uchar_t));
    }
    else
    {
        memcpy(&opt_cfg.stripe_type,opt_ptr, XEL_API_CFG_STRIPE_TOT * sizeof(xel_uchar_t));
    }
	return xel_api_record_opt_type_config(dev_id, &opt_cfg);
}
