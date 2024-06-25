document.addEventListener('DOMContentLoaded', function() {
    const apiUrl = '/pokemon/pokename';
    const pokemonTableBody = document.querySelector('#pokemonTable tbody');
    const pagination = document.getElementById('pagination');
    const previousButton = document.getElementById('previousButton');
    const nextButton = document.getElementById('nextButton');
    const pokemonModal = document.getElementById('pokemonModal');
    const pokemonDetails = document.getElementById('pokemonDetails');
    let currentPage = 0;
    let pageSize = 20;
    let pokemonData = [];
    let currentPokemonIndex = 0;

    function fetchPokemons(page, size) {
        fetch(`${apiUrl}?page=${page}&size=${size}`)
            .then(response => response.json())
            .then(data => {
                if (data && data.results) {
                    pokemonData = data.results;
                    renderPokemons(pokemonData);
                    updatePaginationButtons(page, size, data.count);
                } else {
                    console.error('Error:Error en la data', data);
                }
            })
            .catch(error => console.error('Error al obtener la data:', error));
    }

    function renderPokemons(pokemons) {
        pokemonTableBody.innerHTML = '';
        pokemons.forEach((pokemon, index) => {
            const row = document.createElement('tr');

            const nameCell = document.createElement('td');
            nameCell.textContent = pokemon.name;
            row.appendChild(nameCell);

            const moreInfoCell = document.createElement('td');
            const moreInfoBtn = document.createElement('button');
            moreInfoBtn.textContent = 'Más Información';
            moreInfoBtn.addEventListener('click', function() {
                currentPokemonIndex = index;
                fetchPokemonDetails(pokemon.url);
            });
            moreInfoCell.appendChild(moreInfoBtn);
            row.appendChild(moreInfoCell);

            pokemonTableBody.appendChild(row);
        });
    }

    function updatePaginationButtons(page, size, totalPokemons) {
        const totalPages = Math.ceil(totalPokemons / size);
        previousButton.disabled = page === 0;
        nextButton.disabled = page === totalPages - 1;
    }

    previousButton.addEventListener('click', function() {
        if (currentPage > 0) {
            currentPage--;
            fetchPokemons(currentPage, pageSize);
        }
    });

    nextButton.addEventListener('click', function() {
        currentPage++;
        fetchPokemons(currentPage, pageSize);
    });

    function fetchPokemonDetails(url) {
        fetch(url)
            .then(response => response.json())
            .then(data => {
                showPokemonDetails(data);
            })
            .catch(error => console.error('Error al obtener los detalles:', error));
    }

    function showPokemonDetails(pokemon) {
        pokemonDetails.innerHTML = `
            <h2>${pokemon.name}</h2>
            <p>Base Happiness: ${pokemon.base_happiness}</p>
            <p>Capture Rate: ${pokemon.capture_rate}</p>
            <p>Color: ${pokemon.color.name}</p>
            <p>Egg Groups: ${pokemon.egg_groups.map(group => group.name).join(', ')}</p>
            <p>Evolution Chain: <a href="${pokemon.evolution_chain.url}" target="_blank">Evolution Chain</a></p>
            <p>Evolves From Species: ${pokemon.evolves_from_species ? pokemon.evolves_from_species.name : 'None'}</p>
        `;

        pokemonModal.style.display = 'block';

        const previousPokemonBtn = document.createElement('button');
        previousPokemonBtn.textContent = 'Anterior';
        previousPokemonBtn.addEventListener('click', function() {
            if (currentPokemonIndex > 0) {
                currentPokemonIndex--;
                fetchPokemonDetails(pokemonData[currentPokemonIndex].url);
            }
        });
        pokemonDetails.appendChild(previousPokemonBtn);

        const nextPokemonBtn = document.createElement('button');
        nextPokemonBtn.textContent = 'Siguiente';
        nextPokemonBtn.addEventListener('click', function() {
            if (currentPokemonIndex < pokemonData.length - 1) {
                currentPokemonIndex++;
                fetchPokemonDetails(pokemonData[currentPokemonIndex].url);
            }
        });
        pokemonDetails.appendChild(nextPokemonBtn);

        const closeModalBtn = document.getElementsByClassName('close')[0];
        closeModalBtn.onclick = function() {
            pokemonModal.style.display = 'none';
        };

        window.onclick = function(event) {
            if (event.target == pokemonModal) {
                pokemonModal.style.display = 'none';
            }
        };
    }

    fetchPokemons(currentPage, pageSize);
});
