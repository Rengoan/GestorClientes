<div class="modal fade" id="agregarClienteModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-header bg-info text-white">
            <h5 class="modal-title">Agregar Cliente</h5>
            <button class="close" data-dismiss="modal">
                <span>&times;</span>
            </button>
        </div>
        
        <form action="ServletControlador?accion=insertar" method="POST"
              class="was-validated">
            <div class="modal-body">
                <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input type="text" name="nombre" required/>
                </div>
                <div class="form-group">
                    <label for="apellido">Apellido</label>
                    <input type="text" name="apellido" required/>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" name="email" required/>
                </div>
                <div class="form-group">
                    <label for="telefono">Telefono</label>
                    <input type="tel" name="telefono"/>
                </div>
                <div class="form-group">
                    <label for="saldo">Saldo</label>
                    <input type="number" name="saldo" required/>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="submit">Guardar</button>
            </div>
                
            
        </form>
        
    </div>
</div>