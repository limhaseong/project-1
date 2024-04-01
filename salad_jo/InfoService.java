import java.io.BufferedReader;
import java.util.Iterator;
import java.util.List;

/*
 �ȳ� ----------------------------------------------------------------
*/


public class InfoService implements Imp_info {
    private PdInterface pdInterface;

    // �����ڸ� ����Ͽ� productService �ʱ�ȭ
    public InfoService(ProductService productService) {
        this.pdInterface = productService;
    }

    // ProductType�� �������� ����Ʈ ��� & ���� �� ����
    public void printInfo(ProductType productType) {
        if (productType != ProductType.RCMND){    // ������õ�� �ƴ� ���
            List<Product> productList = pdInterface.getList(productType);

            // ����Ʈ ���
            printInfoHeader(productType);
            printInfoBodyProduct(productType, productList);

            // ���� �� ����
            SelectContinue selectContinue = new SelectContinue();
            selectContinue.menuSelectProduct(productList);
        }else {
            // ����Ʈ ���
            List<MasterRc> masterList = CacheData.list2;
            printInfoHeader(productType);
            printInfoBodyMaster(productType, masterList);

            // ���� �� ����
            SelectContinue selectContinue = new SelectContinue();
            selectContinue.menuSelectMasterRc(masterList);
        }
    }

    // ��� ����Ʈ header
    public void printInfoHeader(ProductType productType) {
        System.out.println("\t---------------------------------------------------------------------------------------------------------------------");
        if(productType != ProductType.RCMND)    // ������õ�� �ƴ� ���
            System.out.printf("\t%-4s| %-8s|\t%-8s|\t%-8s|\t%-8s\t|\t%-8s\n", "��ȣ", "��ǰ��", "����", "Į�θ�", "����", "��������");
        else
            System.out.printf("\t%-4s| %-8s|\t%-8s|\t%-8s\t|\t%-8s|\t%-8s\n", "��ȣ", "��ǰ��", "Į�θ�", "����", "��������", "�����");
        System.out.println("\t---------------------------------------------------------------------------------------------------------------------");
    }

    // ��� ����Ʈ body>Mast
    public void printInfoBodyMaster(ProductType productType, List<MasterRc> productInfo) {
        int index=1;
        for(MasterRc masterRc : productInfo){
            System.out.printf("\t%-4d   %-8s \t%-8d \t%-8d \t%-8d", index++, masterRc.getR_name(), masterRc.getR_totalcalorie(), masterRc.getR_price(), masterRc.getR_count());
            System.out.printf("\t");
            for (Product product : masterRc.getR_products())
            {
                System.out.print(" " + product.getP_name());
            }
            System.out.println();
        }
        System.out.println("\t---------------------------------------------------------------------------------------------------------------------");
    }

    // ��� ����Ʈ body>Product
    public void printInfoBodyProduct(ProductType productType, List<Product> productInfo) {
        if(productType != ProductType.RCMND){   // ������õ�� �ƴ� ���
            int index=1;

            for(Product product : productInfo){
                if(CacheData.orderInnerValues.isEmpty()){  // �ʱⰪ ����
                    product.setP_limitCount(product.getP_count() - product.getP_stock());
                }

                System.out.printf("\t%-4d   %-8s \t%-8s \t%-8s\t \t%-8d \t%-8d\n",
                    index++, product.getP_name(), product.getP_unit(), product.getP_calorie(), product.getP_price(), product.getP_limitCount());
            }
			System.out.println("\t---------------------------------------------------------------------------------------------------------------------");
        }
    } // end: printInfoBodyProduct
}