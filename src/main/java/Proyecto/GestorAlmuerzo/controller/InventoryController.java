
package proyecto.gestoralmuerzo.controller;

import proyecto.gestoralmuerzo.model.Ingredient;
import proyecto.gestoralmuerzo.service.IngredientServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/inventory")
public class InventoryController {

    private final IngredientServices ingredientServices;
    private static final String LINKINICIO ="redirect:/admin/inventory";


    public InventoryController(IngredientServices ingredientServices) {
        this.ingredientServices = ingredientServices;
    }

    @GetMapping
    public String showMenu(Model model) {

        List<Ingredient> ingredients;
        ingredients = ingredientServices.getAllIngredients();

        model.addAttribute("ingredients", ingredients);

        return "/admin/inventory";
    }

    @PostMapping("/addIngredient")
    public String addIngredient(@ModelAttribute("ingredient") Ingredient ingredient) {
        ingredientServices.addIngredient(ingredient);
        return LINKINICIO;
    }

    @GetMapping("/editIngredient/{id}")
    public String showEditPlateForm(@PathVariable String id, Model model) {
        int ingredientId = Integer.parseInt(id);
        Optional<Ingredient> ingredient = ingredientServices.getIngredientById(ingredientId);
        model.addAttribute("ingredient", ingredient);
        return "admin/editIngredient";
    }

    @PostMapping("/editIngredient/{id}")
    public String editPlate(@PathVariable String id, @ModelAttribute("ingredient") Ingredient ingredient) {
        int plateId = Integer.parseInt(id);
        Optional<Ingredient> existingPlate = ingredientServices.getIngredientById(plateId);
        if(existingPlate.isPresent()) {
            ingredient.setId((long) plateId);
            ingredientServices.updateIngredient(ingredient);
        }
        return LINKINICIO;
    }

    @RequestMapping("/deleteIngredient/{id}")
    public String deletePlate(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            long ingredientId = Long.parseLong(id);
            ingredientServices.deleteIngredient(ingredientId);
        }catch (proyecto.gestoralmuerzo.exceptions.GestorAlmuerzosAppException e){
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el ingrediente: " + e.getMessage());
            return "redirect:/admin/inventory?error";
        }
        return LINKINICIO;

    }
}

