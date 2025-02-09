const annotations = [
    {
        name: "@RestController",
        description: "Esta anotación se utiliza para marcar una clase como controlador RESTful. Combina @Controller y @ResponseBody.",
        example: `@RestController
@RequestMapping("/api")
public class UserController {
    // Métodos del controlador
}`,
        keyPoints: [
            "Indica que la clase manejará solicitudes HTTP",
            "Automáticamente serializa las respuestas a JSON/XML",
            "Elimina la necesidad de usar @ResponseBody en cada método"
        ],
        tags: ["Controller", "REST", "Spring MVC"]
    },
    {
        name: "@GetMapping",
        description: "Define un método que maneja solicitudes HTTP GET. Es una versión especializada de @RequestMapping(method = RequestMethod.GET).",
        example: `@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) {
    return userService.findById(id);
}`,
        keyPoints: [
            "Mapea solicitudes HTTP GET a métodos específicos",
            "Puede incluir patrones de URL y variables de ruta",
            "Soporta parámetros de consulta y variables de ruta"
        ],
        tags: ["HTTP", "GET", "Mapping"]
    },
    {
        name: "@RequestParam",
        description: "Se utiliza para extraer parámetros de consulta de la URL y mapearlos a parámetros del método.",
        example: `@GetMapping("/search")
public List<User> searchUsers(
    @RequestParam String name,
    @RequestParam(required = false) Integer age
) {
    return userService.search(name, age);
}`,
        keyPoints: [
            "Extrae parámetros de la URL de consulta",
            "Puede ser opcional con required = false",
            "Soporta valores predeterminados",
            "Puede realizar conversión automática de tipos"
        ],
        tags: ["Parameters", "URL", "Query"]
    }
];

function createAnnotationCard(annotation) {
    return `
        <div class="annotation-card">
            <h2 class="annotation-name">${annotation.name}</h2>
            <p class="annotation-description">${annotation.description}</p>
            <pre class="code-example"><code>${annotation.example}</code></pre>
            <ul class="key-points">
                ${annotation.keyPoints.map(point => `<li>${point}</li>`).join('')}
            </ul>
            <div class="tags">
                ${annotation.tags.map(tag => `<span class="tag">${tag}</span>`).join('')}
            </div>
        </div>
    `;
}

function displayAnnotations(annotationsToShow) {
    const container = document.getElementById('annotationsContainer');
    container.innerHTML = annotationsToShow.map(annotation => createAnnotationCard(annotation)).join('');
}

function setupSearch() {
    const searchInput = document.getElementById('searchInput');
    searchInput.addEventListener('input', (e) => {
        const searchTerm = e.target.value.toLowerCase();
        const filteredAnnotations = annotations.filter(annotation => 
            annotation.name.toLowerCase().includes(searchTerm) ||
            annotation.description.toLowerCase().includes(searchTerm) ||
            annotation.tags.some(tag => tag.toLowerCase().includes(searchTerm))
        );
        displayAnnotations(filteredAnnotations);
    });
}

document.addEventListener('DOMContentLoaded', () => {
    displayAnnotations(annotations);
    setupSearch();
});