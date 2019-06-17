package yamly.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipe {
    private Integer id;

    private String title;

    private String directions;
    
    private Set<Product> products;

    public Recipe() {}

    public Recipe(String title, String directions) {
        this.title = title;
        this.directions = directions;
        this.products = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "directions", columnDefinition = "text", nullable = false)
    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    @ManyToMany(mappedBy = "recipes")
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (!id.equals(recipe.id)) return false;
        if (!title.equals(recipe.title)) return false;
        if (!directions.equals(recipe.directions)) return false;
        return products.equals(recipe.products);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + directions.hashCode();
        result = 31 * result + products.hashCode();
        return result;
    }
}
