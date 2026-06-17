CREATE TABLE IF NOT EXISTS persona (
    persona_id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(20) NOT NULL,
    edad INT NOT NULL,
    identificacion VARCHAR(20) NOT NULL UNIQUE,
    direccion VARCHAR(150) NOT NULL,
    telefono VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS cliente (
    cliente_id BIGINT PRIMARY KEY,
    cliente_id_negocio VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    estado BOOLEAN NOT NULL,
    CONSTRAINT fk_cliente_persona FOREIGN KEY (cliente_id) REFERENCES persona(persona_id) ON DELETE CASCADE
);

-- Insertar datos en la tabla persona
INSERT INTO persona (nombre, genero, edad, identificacion, direccion, telefono) 
VALUES ('Jose Lema', 'Masculino', 35, '1234567890', 'Otavalo sn y principal', '098254785');

INSERT INTO persona (nombre, genero, edad, identificacion, direccion, telefono) 
VALUES ('Marianela Montalvo', 'Femenino', 30, '0987654321', 'Amazonas y NNUU', '097548965');

INSERT INTO persona (nombre, genero, edad, identificacion, direccion, telefono) 
VALUES ('Juan Osorio', 'Masculino', 40, '1122334455', '13 de junio y equinoccial', '098874587');

--crear clientes
INSERT INTO cliente (cliente_id, cliente_id_negocio, contrasena, estado) 
VALUES (1, 'jlema', '1234', true);

INSERT INTO cliente (cliente_id, cliente_id_negocio, contrasena, estado) 
VALUES (2, 'mmontalvo', '5678', true);

INSERT INTO cliente (cliente_id, cliente_id_negocio, contrasena, estado) 
VALUES (3, 'josorio', '1245', true);