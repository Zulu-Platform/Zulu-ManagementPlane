#include "stdlib.h"
#include "stdio.h"
#include "xel_usr_api.h"


int main()
{
    int ret = 0;
    int dev_id = 0;
   
    ret = xel_api_init(dev_id, NULL);
    if (ret != XEL_OK) {
        printf("Failed to int dev_id, ret is %d\n", ret);        
        return -1;
    }

end:
    xel_api_destroy(dev_id);
    
    return ret;
}

