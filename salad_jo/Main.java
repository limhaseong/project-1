import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;


/*
 ���� ----------------------------------------------------------------
*/

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {



//        MemberMg mm = new MemberMg();
//        SalesMg sm = new SalesMg();
//
//        sm.menuDisp();
//        sm.menuSelect();
//        sm.menuRun();

        // ��ü ������ȭ
        FileMg f = new FileMg();
        MemberMg.hm = f.memberFileIn();
        SalesMg.receipts = f.receiptFileIn();
        CacheData.list1 = f.list1FileIn();
        CacheData.list2 = f.list2FileIn();
        CacheData.list3 = f.list3FileIn();
        CacheData.list4 = f.list4FileIn();

        CacheData cacheData = new CacheData();
        cacheData.testData();

        // ������ �α��� ��
        ad_login al = new ad_login();
        al.adLogin();
//
//        // ��ü ���� ��������
       /* f.memberFileOut();
        f.receiptFileOut();*/

//        System.out.println("\n\n\t====[[[[[ ����� ȭ�� ]]]]]====");
//
//        // ȯ���λ� //TODO �ٹж� �ֱ�
//        Emp emp = new Emp(":)");
//        emp.empWelcome();
//
        // ����� ��
        /*ProductService productService = new ProductService(); // ProductService ��ü ����
        Kiosk ks = new Kiosk(productService);
        ks.kioskStart();*/


    }
}
