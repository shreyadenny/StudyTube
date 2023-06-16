package may.internship;

public class ChannelList {

    String name,views,description,price;
    int image;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getViews() {
        return views;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public void setViews(String views) {
        this.views = views;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getImage() {
        return image;
    }
    public void setImage(int image){
        this.image=image;
    }
}
