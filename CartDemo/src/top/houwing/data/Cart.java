package top.houwing.data;
/*
*@author:Houwing
*@date:2018/9/6
*@description:cart结构体
**/
public class Cart {
    private Long id;
    private Long productId;
    private String name;
    private int price;
    private int count;
    private int totalPrice;

    public void incrCart(){
        count++;
        this.totalPrice=price*count;
    }
    public boolean decrCart(){
        count--;
        this.totalPrice=price*count;
        if(count==0){
            return true;
        }
        return false;
    }

    public Cart(Long id, Long productId, String name, int price, int count) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.count = count;
        this.totalPrice=price*count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
