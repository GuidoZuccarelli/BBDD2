package modelo;

public class Master {
private long id;

private Cart carts;

private Site sites;

public Master(){
	carts = new Cart();
	sites = new Site();
}

public Long getId() {
    return id;
}

private void setId(Long id) {
    this.id = id;
}

}
