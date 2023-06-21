package may.internship;

public class CartList {
    String name,price,unit,description,qty,views;
    int image;
    boolean isWishlist = false;
    boolean isCart = false;

    public boolean isCart() {
        return isCart;
    }

    public void setCart(boolean cart) {
        isCart = cart;
    }

    public boolean isWishlist() {
        return isWishlist;
    }

    public void setWishlist(boolean wishlist) {
        isWishlist = wishlist;
    }


    public String getName() {
        return name;
    }
    public void setViews(String views) {
        this.views = views;
    }
    public String getViews() {
        return views;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
