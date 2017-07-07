/* 
 * File:   main.c
 * Author: dubel
 *
 * Created on June 10, 2015, 5:21 PM
 */

#include <stdio.h>
#include <stdlib.h>
#include "xel_app.h"
#include "xel_usr_api.h"

#define KEY_SIZE 576

static unsigned char g_record_data[KEY_SIZE];
static unsigned char g_record_mask[KEY_SIZE];
static unsigned char g_search_key[KEY_SIZE];

static int app_search_576(int dev_id, int ltr_id)
{
    int ret = 0;
    xel_api_search_key_t search_key;
    xel_api_result_t     search_result;

    memset(&search_key, 0, sizeof(search_key));
    memset(&search_result, 0, sizeof(search_result));
    search_key.size = KEY_SIZE;
    search_key.key_ptr = g_search_key;

    /* check lower limit */
    g_search_key[8] = g_search_key[4] = 0x1;
    g_search_key[10] = g_search_key[6] = 0x6;    
	ret = xel_api_search(get_channel_id(dev_id), ltr_id, &search_key, &search_result);	
    if (ret != XEL_OK) {
        PRINT("Failed to xel_api_search\n");
        return -1;
    }
    
    if ((search_result.result0 & 0x80000000) == 0) {
        PRINT("Not find match for lower limit\n");
    }

    /* check upper limit */
    g_search_key[8] = g_search_key[4] = 0x3;
    g_search_key[10] = g_search_key[6] = 0x8;
	ret = xel_api_search(get_channel_id(dev_id), ltr_id, &search_key, &search_result);	
    if (ret != XEL_OK) {
        PRINT("Failed to xel_api_search\n");
        return -1;
    }
    
    if ((search_result.result0 & 0x80000000) == 0) {
        PRINT("Not find match for upper limit\n");
    }

    /* check middle data */
    g_search_key[8] = g_search_key[4] = 0x2;
    g_search_key[10] = g_search_key[6] = 0x7;
	ret = xel_api_search(get_channel_id(dev_id), ltr_id, &search_key, &search_result);	
    if (ret != XEL_OK) {
        PRINT("Failed to xel_api_search\n");
        return -1;
    }
    
    if ((search_result.result0 & 0x80000000) == 0) {
        PRINT("Not find match for middle data\n");
    }

    /* check out of range */
    g_search_key[8] = g_search_key[4] = 0x0;
    g_search_key[10] = g_search_key[6] = 0x0;
	ret = xel_api_search(get_channel_id(dev_id), ltr_id, &search_key, &search_result);	
    if (ret != XEL_OK) {
        PRINT("Failed to xel_api_search\n");
        return -1;
    }
    
    if ((search_result.result0 & 0x80000000) != 0) {
        PRINT("find match for out of range key\n");
    }    
    
    return 0;
}


static int app_add_576(int dev_id, int db_id, int record_id, int opt_index)
{
	xel_api_record_t test_record;
    
    memset(g_record_data, 0, KEY_SIZE);
    memset(g_record_mask, 0xff, KEY_SIZE);
    
    g_record_data[0] = 0x01;
    g_record_data[1] = 0x02;
    g_record_data[2] = 0x03;
    g_record_data[3] = 0x04;
    
    g_record_mask[0] = 0;
    g_record_mask[1] = 0;
    g_record_mask[2] = 0;
    g_record_mask[3] = 0;

    g_record_data[4] = 0x1;
    g_record_data[8] = 0x4;     /* 0x3 + 1 */
    
    g_record_data[6] = 0x6;
    g_record_data[10] = 0x9;    /* 0x8 + 1 */

    /* store keys, just for search test */
    memcpy(g_search_key, g_record_data, KEY_SIZE);    

    memset(&test_record, 0, sizeof(test_record));
    test_record.record_database = db_id;
	test_record.record_id = record_id;
	test_record.record_opt = opt_index;
    test_record.record_data_ptr = g_record_data;
    test_record.record_mask_ptr = g_record_mask;
    
    return xel_api_add_record(dev_id, &test_record);
}

int main()
{
    int ret = 0;
    int dev_id = 0;
    int keysize = KEY_SIZE;
    int table_id = 0;
    int db_id = 10, bank = 0;
    int ltr_id = 31;
    int record_id = 100;
    int opt_index = 15;
    char stripe_type[XEL_API_CFG_STRIPE_TOT];

    ret = xel_api_init(dev_id, NULL);
    if (ret != XEL_OK) {
        PRINT("Failed to int dev_id, ret is %d\n", ret);        
        return -1;
    }

    /* set opt_index, set the operator of first strip with "less than" */
    memset(&stripe_type, XEL_API_OPT_TYPE_EQ, XEL_API_CFG_STRIPE_TOT * sizeof(xel_uchar_t));
    stripe_type[2] = XEL_API_OPT_TYPE_GE;
    stripe_type[3] = XEL_API_OPT_TYPE_GE;
    stripe_type[4] = XEL_API_OPT_TYPE_LT;
    stripe_type[5] = XEL_API_OPT_TYPE_LT;
    ret = xel_app_config_opt(dev_id, keysize, opt_index, stripe_type);
    if (ret != XEL_OK) {
        PRINT("Failed to config opt index, ret is %d\n", ret);
        return -1;
    }

    ret = xel_app_create_db(dev_id, db_id, keysize, bank, table_id);
    if (ret != XEL_OK) {
        PRINT("Failed to create database, ret is %d\n", ret);
        goto end;
    }

    ret = xel_app_config_ltr(dev_id, ltr_id, db_id);
    if (ret != XEL_OK) {
        PRINT("Failed to config ltr, ret is %d\n", ret);
        goto end;
    }

    ret = app_add_576(dev_id, db_id, record_id, opt_index);
    if (ret != XEL_OK) {
        PRINT("Failed to create database, ret is %d\n", ret);
        goto end;
    }

    ret = app_search_576(dev_id, ltr_id);
    if (ret != 0) {
        PRINT("Failed to range search\n");
        goto end;
    }

end:
    xel_app_config_ltr(dev_id, ltr_id, -1);
    xel_api_database_remove(dev_id, db_id);
    xel_api_destroy(dev_id);
    
    return ret;
}



