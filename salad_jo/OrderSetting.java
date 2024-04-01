import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.StringTokenizer;

import java.util.Date;

// �ֹ�
public class OrderSetting {
    void calculateOrderTotal() {            // ���� ���� ���� �� Į�θ�, �� ���� ���
        List<Order> OrderList = CacheData.orderOuterList;
        List<OrderValues> orderValueList = CacheData.orderInnerValues;

        OrderCart oC = new OrderCart();
        OrderList.get(OrderList.size()-1).setO_nowTime(oC.nowTime());

        if (OrderList.isEmpty()) {
            OrderList.add(new Order());
            OrderList.get(OrderList.size()-1).setO_nowTime(oC.nowTime());
        }
        else {
            OrderList.get(OrderList.size()-1).setO_nowTime(oC.nowTime());
        }

        for (Order order: OrderList){
            order.setO_totCalorie(0);
            order.setO_totPrice(0);
        }
        for (Order order: OrderList){
          for (OrderValues orderValues: orderValueList){
              order.setO_totCalorie(order.getO_totCalorie()+(orderValues.getCalorie() * orderValues.getCount()));
              order.setO_totPrice(order.getO_totPrice()+(orderValues.getPrice() * orderValues.getCount()));
          }
        }
    }

    void calculateOrderDiscount() {            // ���� ���� ���� ������� ���ð� ���ֱ�
//        list1
        List<Product> List1 = CacheData.list1;  //��� ����Ʈ
        List<Order> OrderList = CacheData.orderOuterList;  //��ٱ��� ����Ʈ
        List<OrderValues> orderValueList = CacheData.orderInnerValues; //���� ����Ʈ
        int productIdx = 0;
        for (Order order: OrderList){
            for (OrderValues orderValues: orderValueList){
                for(Product list1 : List1){
                    if(list1.getP_name().equals(orderValues.getName())){
                        productIdx = List1.indexOf(list1);
                        break;
                    }
                }
                int productCount = List1.get(productIdx).getP_count();
                int productDiscount = orderValues.getCount();
                List1.get(productIdx).setP_count(productCount - productDiscount);
           }
        }
        // list3
        List<Product> List3 = CacheData.list3;
        int productIdx2 = 0;
        for (Order order: OrderList){
            for (OrderValues orderValues: orderValueList){
                for(Product list3 : List3){
                    if(list3.getP_name().equals(orderValues.getName())){
                        productIdx2 = List3.indexOf(list3);
                        break;
                    }
                }
                int productCount = List3.get(productIdx2).getP_count();
                int productDiscount = orderValues.getCount();
                List3.get(productIdx2).setP_count(productCount - productDiscount);
            }
        }

    }

    void printOrderList() {             // ���� �� ���
        List<Order> OrderList = CacheData.orderOuterList;
        List<OrderValues> orderInnerValues = CacheData.orderInnerValues;
        System.out.println("\n\t[ ���� ��ٱ��� ]");
        System.out.println("\t--------------------------------------------------------------------------");
        System.out.printf("\t%-4s| %-8s \t%-8s \t%-8s \t%-8s\n", "NO", "�̸�", "����Ͻð�", "��Į�θ�", "�ѱݾ�");
        System.out.println("\t--------------------------------------------------------------------------");

        int i=1,j=1;
        for (Order order: OrderList){
            System.out.printf("\t%-4d   %-8s \t%-8s \t%-8d \t%-8d\n", i++, order.getO_name(), order.getO_nowTime(), order.getO_totCalorie(), order.getO_totPrice());
            System.out.printf("\t%-4s) %-4s \t%-4s \t%-8s \t%-8s\n", "NO", "��ǰ", "���ż���", "Į�θ�", "�ݾ�");
            for (OrderValues orderValues: orderInnerValues) {
                System.out.printf("\t%d-%d) %-8s \t%-8s \t%-8d \t%-8d\n", i-1 , j++, orderValues.getName(), orderValues.getCount(), orderValues.getCalorie() * orderValues.getCount(), orderValues.getPrice() * orderValues.getCount());
            }
        }
        System.out.println("\t--------------------------------------------------------------------------");

    }

}