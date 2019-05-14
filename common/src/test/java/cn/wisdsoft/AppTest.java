package cn.wisdsoft;

import static org.junit.Assert.assertTrue;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test(){
        Md5Hash md5Hash;
        //加密：MD5 + 盐 + 散列次数
        md5Hash = new Md5Hash("17120801002","this",3);
        System.out.println(md5Hash);
        String x = "b9d6a6af2b0e63bccf248c1fab387b6a";
        String y = "b9d6a6af2b0e63bccf248c1fab387b6a";
        String s = x.toUpperCase();
        System.out.println(s);
    }
}
