package top.houwing.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
*@author:Houwing
*@date:2018/9/5
*@description:本地缓存
**/
public class LocalCache {
   private static Map<Long,Product> productMap=new HashMap<>();
    private static Map<Long,Cart> cartMap=new HashMap<>();
    private static Map<Long,Product> favouriteMap=new HashMap<>();
    private static Map<Long,Product> browseLogMap=new HashMap<>();

    static {
        productMap.put(1l,new Product(1l,"Java","Course1","Java基础教程8小时带领大家步步深入学习标签用法和意义","A",233));
        productMap.put(2l,new Product(2l,"Java","Course2","Java基础教程8小时带领大家步步深入学习标签用法和意义","A",233));
        productMap.put(3l,new Product(3l,"Java","Course3","Java基础教程8小时带领大家步步深入学习标签用法和意义","A",233));
        productMap.put(4l,new Product(4l,"Hadoop","Course4","Hadoop基础教程8小时带领大家步步深入学习标签用法和意义","A",233));
        productMap.put(5l,new Product(5l,"Hadoop","Course5","Hadoop基础教程8小时带领大家步步深入学习标签用法和意义","A",233));
        productMap.put(6l,new Product(6l,"Hadoop","Course6","Hadoop基础教程8小时带领大家步步深入学习标签用法和意义","B",233));
        productMap.put(7l,new Product(7l,"Hadoop","Course7","Hadoop基础教程8小时带领大家步步深入学习标签用法和意义","B",233));
        productMap.put(8l,new Product(8l,"HTML/CSS","Course8","HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义","B",233));
        productMap.put(9l,new Product(9l,"HTML/CSS","Course9","HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义","B",233));
        productMap.put(10l,new Product(10l,"HTML/CSS","Course10","HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义","B",233));
        productMap.put(11l,new Product(11l,"HTML/CSS","Course11","HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义","C",233));
        productMap.put(12l,new Product(12l,"HTML/CSS","Course12","HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义","C",233));
        productMap.put(13l,new Product(13l,"HTML/CSS","Course13","HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义","C",233));
        productMap.put(14l,new Product(14l,"HTML/CSS","Course14","HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义","C",233));
        productMap.put(15l,new Product(15l,"HTML/CSS","Course15","HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义","C",233));
        productMap.put(16l,new Product(16l,"HTML/CSS","Course16","HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义","C",233));
        productMap.put(17l,new Product(17l,"HTML/CSS","Course17","HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义","C",233));
        productMap.put(18l,new Product(18l,"HTML/CSS","Course18","HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义","D",233));
        productMap.put(19l,new Product(19l,"HTML/CSS","Course19","HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义","D",233));
    }




    public static Product getProduct(Long Id){
        Product product=productMap.get(Id);
        return product;
    }

    public static void incrCart(Long productId){

        cartMap.get(productId).incrCart();
    }

    public static void decrCart(Long productId){
        boolean result=cartMap.get(productId).decrCart();
        if(result){
            cartMap.remove(productId);
        }
    }

    public static void addCart(Product product){
        if(!cartMap.containsKey(product.getId())){
            cartMap.put(product.getId(),new Cart(product.getId(),product.getId(),product.getName(),product.getPrice(),1));
        }else {
            incrCart(product.getId());
        }
    }

    public static void delCart(Long productId){
        cartMap.remove(productId);
    }

    public static void addFavourite(Product product){
        if(!favouriteMap.containsKey(product.getId())){
            favouriteMap.put(product.getId(),product);
        }
    }

    public static void deleteFavourite(Long Id){
        if(favouriteMap.containsKey(Id)){
            favouriteMap.remove(Id);
        }
    }

    public static Cart getCart(Long id){
        return cartMap.get(id);
    }

    public static void addBrowseLog(Product product){
        browseLogMap.put(product.getId(),product);
    }

    public static void deleteBroeseLog(Long id){
        if(browseLogMap.containsKey(id)){
            browseLogMap.remove(id);
        }
    }


    /*
    * 获取列表
    * */
    public static List<Cart> getCarts(){
        return new ArrayList<>(cartMap.values());
    }

    public static List<Product> getProductMap(){
        return new ArrayList<Product>(productMap.values());
    }

    public static List<Product> getFavourites(){
        return new ArrayList<Product>(favouriteMap.values());
    }

    public static List<Product> getBrowseLogs(){
        return new ArrayList<Product>(browseLogMap.values());
    }

    public static List<Product> getProducts(int page,int size,String name) {
        /*
        *@author:gorden
        *@date:2018/9/18
        *@description:获取符合条件的产品列表
        *@parameter:[page, size, name]
        **/
        List<Product> products=new ArrayList<>();

        if(null!=name && !"".equals(name)){
            productMap.values().forEach(product -> {
                if(product.getTag().contains(name) || product.getDesc().contains(name))
                    products.add(product);
            });
        }else {
            products.addAll(productMap.values());
        }

        int start=(page-1)*size;
        int end=products.size() >= page*size ? page*size : products.size();
        return products.subList(start,end);
    }

    public static int getProductCount(String name) {
        List<Product> products=new ArrayList<>();

        if(null!=name && !"".equals(name)){
            productMap.values().forEach(product -> {
                if(product.getTag().contains(name) || product.getDesc().contains(name))
                    products.add(product);
            });
        }else {
            products.addAll(productMap.values());
        }

        return products.size();
    }
}
