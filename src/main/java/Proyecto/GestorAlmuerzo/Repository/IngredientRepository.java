package proyecto.gestoralmuerzo.repository;

import proyecto.gestoralmuerzo.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Ingredient findByName(String name);

}

