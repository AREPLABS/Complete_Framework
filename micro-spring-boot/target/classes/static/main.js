const books = [
    {
        title: "Don Quijote de la Mancha",
        author: "Miguel de Cervantes",
        cover: "https://images.unsplash.com/photo-1544947950-fa07a98d237f?auto=format&fit=crop&q=80&w=400",
        description: "La historia del ingenioso hidalgo Don Quijote de la Mancha y sus aventuras con su fiel escudero Sancho Panza."
    },
    {
        title: "Cien años de soledad",
        author: "Gabriel García Márquez",
        cover: "https://images.unsplash.com/photo-1543002588-bfa74002ed7e?auto=format&fit=crop&q=80&w=400",
        description: "La saga de la familia Buendía a través de siete generaciones en el pueblo mítico de Macondo."
    },
    {
        title: "El Principito",
        author: "Antoine de Saint-Exupéry",
        cover: "https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?auto=format&fit=crop&q=80&w=400",
        description: "Un pequeño príncipe viaja por diferentes planetas, aprendiendo sobre la vida y el amor."
    },
    {
        title: "1984",
        author: "George Orwell",
        cover: "https://images.unsplash.com/photo-1541963463532-d68292c34b19?auto=format&fit=crop&q=80&w=400",
        description: "Una distopía que explora temas de vigilancia gubernamental, totalitarismo y manipulación de la verdad."
    },
    {
        title: "Rayuela",
        author: "Julio Cortázar",
        cover: "https://images.unsplash.com/photo-1532012197267-da84d127e765?auto=format&fit=crop&q=80&w=400",
        description: "Una novela experimental que puede leerse de múltiples maneras, siguiendo diferentes órdenes de capítulos."
    }
];

function createBookCard(book) {
    return `
        <div class="book-card">
            <img src="${book.cover}" alt="${book.title}" class="book-cover">
            <div class="book-info">
                <h2 class="book-title">${book.title}</h2>
                <p class="book-author">${book.author}</p>
                <p class="book-description">${book.description}</p>
            </div>
        </div>
    `;
}

function displayBooks(booksToShow) {
    const bookContainer = document.getElementById('bookContainer');
    bookContainer.innerHTML = booksToShow.map(book => createBookCard(book)).join('');
}

function setupSearch() {
    const searchInput = document.getElementById('searchInput');
    searchInput.addEventListener('input', (e) => {
        const searchTerm = e.target.value.toLowerCase();
        const filteredBooks = books.filter(book => 
            book.title.toLowerCase().includes(searchTerm) ||
            book.author.toLowerCase().includes(searchTerm)
        );
        displayBooks(filteredBooks);
    });
}

// Initial setup
document.addEventListener('DOMContentLoaded', () => {
    displayBooks(books);
    setupSearch();
});