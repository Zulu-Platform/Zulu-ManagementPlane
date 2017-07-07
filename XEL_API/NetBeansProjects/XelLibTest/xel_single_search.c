/* ============================================================================
 *
 * Copyright (c) 2009-2010 XeL Technology Inc., all rights reserved.
 * xel_single_search.c
 *
 * This application reference is about single search,
 * and the steps are as follows.
 * 
 * 1. init device
 * 2. create database with key size, bank id, table id
 * 3. config ltr, only set database_id0
 * 4. add record
 * 5. search record
 * 6. remove ltr, set database with -1
 * 7. remove database
 * 8. destroy device
 * ========================================================================= */

#include "xel_usr_api.h"
#include "xel_app.h"

static char g_search_data[][72] = {
      {
		   0x55,0x55,0x55,0x55,0x55,0x55,0x55,0x55,0x55,
		   0x55,0x55,0x55,0x55,0x55,0x55,0x55,0x55,0x85,
		   0x21,0x22,0x23,0x24,0x25,0x26,0x27,0x28,0x29,
		   0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x39,
		   0x41,0x42,0x43,0x44,0x45,0x46,0x47,0x48,0x49,
		   0x51,0x52,0x53,0x54,0x55,0x56,0x57,0x58,0x59,
		   0x61,0x62,0x63,0x64,0x65,0x66,0x67,0x68,0x69,
		   0x71,0x72,0x73,0x74,0x75,0x76,0x77,0x78,0x79,
	  },
};

static char g_search_mask[72] =	{
	   0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0xff,
	   0x02,0x00,0x00,0x00,0x00,0x00,0x76,0x00,0xff,
	   0x04,0x00,0x00,0x00,0x00,0x00,0x22,0x00,0x00,
	   0x09,0x00,0x00,0x00,0x00,0x00,0xe1,0x00,0x00,
	   0x11,0x00,0x00,0x00,0x00,0x00,0xdd,0x00,0x00,
	   0x00,0x44,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
	   0x00,0x00,0x59,0x00,0x00,0x00,0x00,0x00,0x00,
	   0x00,0x00,0x00,0xff,0x03,0xf0,0x00,0x00,0x00,
};

static int app_single_search(int dev_id, int ltr_id, char *key, int keysize)
{
    int ret = 0;
    xel_api_search_key_t search_key;
    xel_api_result_t     search_result;

    memset(&search_key, 0, sizeof(search_key));
    memset(&search_result, 0, sizeof(search_result));

    search_key.size = keysize;
    search_key.key_ptr = (unsigned char *)key;

	ret = xel_api_search(get_channel_id(dev_id), ltr_id, &search_key, &search_result);	
    if (ret != XEL_OK)
    {
        PRINT("Failed to xel_api_search\n");
        return -1;
    }
    if ((search_result.result0 & 0x80000000) == 0)
    {
        PRINT("Not find match for ltr_id(%d), key_size(%d)\n", ltr_id, keysize);
        return -1;
    }
    return 0;
}

int app_ssearch_main(void)
{
    int ret = 0;
    int dev_id = 0;
    int keysize = 144;
    int db_id = 0, bank = 0, table_id = 0;
    int ltr_id = 31;
    int record_id = 0;

    ret = xel_api_init(dev_id, NULL);
    if (ret != XEL_OK)
    {
        PRINT("Failed to int dev_id, ret is %d\n", ret);        
        return -1;
    }

    ret = xel_app_create_db(dev_id, db_id, keysize, bank, table_id);
    if (ret != XEL_OK)
    {
        PRINT("Failed to create database, ret is %d\n", ret);
        goto end;
    }

    /* -1 means useless database id */
    ret = xel_app_config_ltr(dev_id, ltr_id, db_id);
    if (ret != XEL_OK)
    {
        PRINT("Failed to config ltr, ret is %d\n", ret);
        goto end;
    }

    /* opt_index 0 means equal match */
    ret = xel_app_add_record(dev_id, db_id, record_id, 0, g_search_data[0], g_search_mask);
    if (ret != XEL_OK)
    {
        PRINT("Failed to create database, ret is %d\n", ret);
        goto end;
    }

    ret = app_single_search(dev_id, ltr_id, g_search_data[0], keysize);
    if (ret != 0)
    {
        PRINT("Failed to single search\n");
        goto end;
    }
    ret = 0;

end:
    /* -1 means useless database id */
    xel_app_config_ltr(dev_id, ltr_id, -1);
    xel_api_database_remove(dev_id, db_id);
    xel_api_destroy(dev_id);
    return ret;
}

