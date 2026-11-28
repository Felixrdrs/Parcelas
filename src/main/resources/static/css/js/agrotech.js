const modalEliminar = document.getElementById('modalEliminar');
if (modalEliminar) {
    modalEliminar.addEventListener('show.bs.modal', (evento) => {
        const boton = evento.relatedTarget;
        if (!boton) return;
        modalEliminar.querySelector('#modalEliminarForm').action = boton.dataset.url;
        modalEliminar.querySelector('#modalEliminarNombre').textContent = boton.dataset.nombre;
    });
}