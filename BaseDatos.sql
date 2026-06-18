-- =============================================================
-- BASE DE DATOS: bancodb  (cliente-persona-service)
-- =============================================================

CREATE TABLE IF NOT EXISTS persona (
    persona_id SERIAL PRIMARY KEY,
    nombre     VARCHAR(100) NOT NULL,
    genero     VARCHAR(20)  NOT NULL,
    edad       INT          NOT NULL,
    identificacion VARCHAR(20) NOT NULL UNIQUE,
    direccion  VARCHAR(150) NOT NULL,
    telefono   VARCHAR(20)  NOT NULL
);

CREATE TABLE IF NOT EXISTS cliente (
    cliente_id         BIGINT       PRIMARY KEY,
    cliente_id_negocio VARCHAR(50)  NOT NULL UNIQUE,
    contrasena         VARCHAR(255) NOT NULL,
    estado             BOOLEAN      NOT NULL,
    CONSTRAINT fk_cliente_persona
        FOREIGN KEY (cliente_id) REFERENCES persona(persona_id) ON DELETE CASCADE
);

-- Personas
INSERT INTO persona (nombre, genero, edad, identificacion, direccion, telefono)
VALUES ('Jose Lema',          'Masculino', 35, '1234567890', 'Otavalo sn y principal',   '098254785');

INSERT INTO persona (nombre, genero, edad, identificacion, direccion, telefono)
VALUES ('Marianela Montalvo', 'Femenino',  30, '0987654321', 'Amazonas y NNUU',          '097548965');

INSERT INTO persona (nombre, genero, edad, identificacion, direccion, telefono)
VALUES ('Juan Osorio',        'Masculino', 40, '1122334455', '13 de junio y equinoccial','098874587');

-- Clientes (cliente_id referencia persona_id generado arriba: 1, 2, 3)
INSERT INTO cliente (cliente_id, cliente_id_negocio, contrasena, estado)
VALUES (1, 'jlema',     '1234', true);

INSERT INTO cliente (cliente_id, cliente_id_negocio, contrasena, estado)
VALUES (2, 'mmontalvo', '5678', true);

INSERT INTO cliente (cliente_id, cliente_id_negocio, contrasena, estado)
VALUES (3, 'josorio',   '1245', true);


-- =============================================================
-- BASE DE DATOS: cuentadb  (cuenta-movimiento-service)
-- =============================================================

CREATE TABLE IF NOT EXISTS cuenta (
    id            BIGSERIAL PRIMARY KEY,
    numero_cuenta VARCHAR(20)    NOT NULL UNIQUE,
    tipo_cuenta   VARCHAR(30)    NOT NULL,
    saldo_inicial DECIMAL(15, 2) NOT NULL,
    saldo_actual  DECIMAL(15, 2) NOT NULL,
    estado        BOOLEAN        NOT NULL,
    cliente_id    VARCHAR(50)    NOT NULL
);

CREATE TABLE IF NOT EXISTS movimiento (
    id              BIGSERIAL PRIMARY KEY,
    fecha           TIMESTAMP      NOT NULL,
    tipo_movimiento VARCHAR(20)    NOT NULL,
    valor           DECIMAL(15, 2) NOT NULL,
    saldo           DECIMAL(15, 2) NOT NULL,
    cuenta_id       BIGINT         NOT NULL,
    CONSTRAINT fk_movimiento_cuenta
        FOREIGN KEY (cuenta_id) REFERENCES cuenta(id) ON DELETE CASCADE
);

-- Cuentas
INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, saldo_actual, estado, cliente_id)
VALUES ('478758', 'Ahorro',    2000.00, 2000.00, true, 'jlema');

INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, saldo_actual, estado, cliente_id)
VALUES ('225487', 'Corriente',  100.00,  100.00, true, 'mmontalvo');

INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, saldo_actual, estado, cliente_id)
VALUES ('495878', 'Ahorro',     540.00,  540.00, true, 'josorio');

INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, saldo_actual, estado, cliente_id)
VALUES ('496825', 'Ahorro',     540.00,  540.00, true, 'mmontalvo');

INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, saldo_actual, estado, cliente_id)
VALUES ('585545', 'Corriente', 1000.00, 1000.00, true, 'jlema');

-- Movimientos
-- Cuenta 478758 (jlema - Ahorro): débito -575
INSERT INTO movimiento (fecha, tipo_movimiento, valor, saldo, cuenta_id)
VALUES ('2025-01-15 10:00:00', 'Debito', -575.00, 1425.00, 1);

-- Cuenta 225487 (mmontalvo - Corriente): depósito +600
INSERT INTO movimiento (fecha, tipo_movimiento, valor, saldo, cuenta_id)
VALUES ('2025-01-15 11:00:00', 'Deposito', 600.00, 700.00, 2);

-- Cuenta 495878 (josorio - Ahorro): depósito +150
INSERT INTO movimiento (fecha, tipo_movimiento, valor, saldo, cuenta_id)
VALUES ('2025-01-15 12:00:00', 'Deposito', 150.00, 690.00, 3);

-- Cuenta 496825 (mmontalvo - Ahorro): débito -540
INSERT INTO movimiento (fecha, tipo_movimiento, valor, saldo, cuenta_id)
VALUES ('2025-01-15 13:00:00', 'Debito', -540.00, 0.00, 4);

-- Cuenta 585545 (jlema - Corriente): depósito +1000
INSERT INTO movimiento (fecha, tipo_movimiento, valor, saldo, cuenta_id)
VALUES ('2025-01-15 14:00:00', 'Deposito', 1000.00, 2000.00, 5);
