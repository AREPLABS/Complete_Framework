console.log("Hello from main.js");

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
    // ... otras anotaciones
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