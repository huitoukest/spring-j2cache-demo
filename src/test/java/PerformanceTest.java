import com.alibaba.fastjson.JSON;
import com.tingfeng.test.SpringCacheTestApp;
import com.tingfeng.test.bean.TestBean;
import com.tingfeng.test.utils.TestUtils;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * 性能测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringCacheTestApp.class})
public class PerformanceTest {

    public static final int DATA_COUNT = 1 * 10000; //x * 1w;
    public static final int THREAD_COUNT = 1;
    public static final int READ_COUNT = 1000;
    public static final String KEY = "testKey";
    public static final String VALUE = "Failed to retrieve plugin descriptor Failed to retrieve plugin descriptorFailed to retrieve plugin descriptorFailed to retrieve plugin descriptorFailed to retrieve plugin descriptor ";
    public static final long EXPIRE_TIME_SECOND = 60;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private CacheChannel cacheChannel;

    /**
     * use time:1888886
     */
    @Test
    public void redisTest(){
        TestUtils.printTime(THREAD_COUNT,DATA_COUNT,(i)->{
            redisTemplate.opsForValue().set(KEY,VALUE,EXPIRE_TIME_SECOND,TimeUnit.SECONDS);
            for(int j = 0 ; j < READ_COUNT ; j++){
                String value = (String) redisTemplate.opsForValue().get(KEY);
                if(j == 0 && i % 1000 == 0){
                       System.out.println(i + "=" + i + ";j=" + j + "; value = " + value);
                }
            }
            return  null;
        });
    }

    /**
     * use time : 10193
     */
    @Test
    public void j2cacheTest(){
        TestUtils.printTime(THREAD_COUNT,DATA_COUNT,(i)->{
            cacheChannel.set("testCache",KEY,VALUE);
            for(int j = 0 ; j < READ_COUNT ; j++){
                CacheObject v = cacheChannel.get("testCache",KEY,true);
                if(j == 0 && i % 1000 == 0){
                    System.out.println(i + "=" + i + ";j=" + j + "; value = " + v.getValue());
                }
            }
            return  null;
        });
    }


    @Test
    public void redisExpireTest(){
        String key = "aaa:";
        for(int i = 0 ;i < 10 ;i++){
            redisTemplate.opsForValue().set(key + i,VALUE);
        }
        redisTemplate.expire(key,EXPIRE_TIME_SECOND,TimeUnit.SECONDS);
        redisTemplate.expire(key.split(":")[0],EXPIRE_TIME_SECOND,TimeUnit.SECONDS);
    }

}
