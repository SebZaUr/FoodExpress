package proyecto.gestoralmuerzo.service;


import proyecto.gestoralmuerzo.repository.IngredientRepository;
import proyecto.gestoralmuerzo.repository.PlateRepository;
import proyecto.gestoralmuerzo.model.Ingredient;
import proyecto.gestoralmuerzo.model.Plate;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlateServices {
    private final PlateRepository plateRepository;
    private final IngredientRepository ingredientRepository;

    public PlateServices(PlateRepository plateRepository, IngredientRepository ingredientRepository) {
        this.plateRepository = plateRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<Plate> getAllPlates() {
        return plateRepository.findAll();
    }

    public Optional<Plate> getPlateById(long id) {
        return plateRepository.findById(id);
    }

    public Plate addPlate(Plate plate) {

        return plateRepository.save(plate);
    }

    @Transactional
    public void updatePlate(Plate plate) {
        plateRepository.save(plate);
    }

    public void deletePlate(long id) {
        plateRepository.deleteById(id);
    }

    public List<Plate> getAllPlatesOrderedByName() {
        return plateRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public List<Plate> getAllPlatesOrderedByPrice() {
        return plateRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));

    }

    public List<Plate> findByCategoriesId(Long categoryId) {
        return plateRepository.findByCategoriesId(categoryId);
    }

    public List<Plate> getFilteredPlates(List<Plate> allPlates, List<Ingredient> preferences, List<Ingredient> bannedIngredients) {
        List<Plate> filteredPlates = new ArrayList<>();

        for (Plate plate : allPlates) {
            if (plateContainsIngredients(plate, preferences)) {
                filteredPlates.add(plate);
            }
        }

        for (Plate plate : allPlates) {
            if(!bannedIngredients.isEmpty()) {
                if (!plateContainsIngredients(plate, bannedIngredients) && !filteredPlates.contains(plate)) {
                    filteredPlates.add(plate);
                }
            }else {
                if (!filteredPlates.contains(plate)){
                    filteredPlates.add(plate);
                }
            }
        }
        return filteredPlates;
    }

    protected boolean plateContainsIngredients(Plate plate, List<Ingredient> ingredients) {
        int cont = 0;
        for (Ingredient ingredient : ingredients) {
            if (plate.getIngredients().contains(ingredient)) {
                cont++;
            }
        }
        return cont>0;
    }


}

