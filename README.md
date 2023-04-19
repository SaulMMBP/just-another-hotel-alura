# Challenge ONE | Java | Back-end | Hotel Alura

<p align="center" >
     <img width="300" src="https://user-images.githubusercontent.com/91544872/189419040-c093db78-c970-4960-8aca-ffcc11f7ffaf.png">
</p>
<p align="center">
     <a href="https://www.linkedin.com/company/oracle/">
          <img src="https://img.shields.io/badge/linkedin:-🧡 Oracle-C74634?style=for-the-badge&logo=linkedin"/>
     </a>
     <a href="https://www.linkedin.com/company/alura-latam/mycompany/">
          <img src="https://img.shields.io/badge/linkedin:-💙 Alura Latam-061E3C?style=for-the-badge&logo=linkedin"/>
     </a>
</p>

---
## 🖥️ Tecnologías Utilizadas:

[![Tecnologías](https://skillicons.dev/icons?i=java,eclipse,mysql&theme=dark)](https://skillicons.dev)

- JCalendar
- JDBC
- c3p0

---

**Para este desafío, se nos facilitó el proyecto base con la interfaz ya diseñada, para concentrarnos en la parte lógica de la aplicación.**

---
## 📊 Base de Dados
Para este reto se propusieron dos tablas: **reservas** y **huespedes**. La tabla de reservas debe contener la clave externa *(foreign key)* **huesped_id**.

```mermaid
erDiagram
     huespedes||--o{ reservas : realiza

     huespedes {
          BIGINT id
          VARCHAR nombre
          VARCHAR nombre
          DATE fecha_nacimiento
          VARCHAR nacionalidad
          VARCHAR telefono
     }

     reservas {
          BIGINT id
          DATE fecha_entrada
          DATE fecha_salida
          VARCHAR valor
          VARCHAR forma_pago
          BIGINT huesped_id
     }

```

## &#128247; Capturas

||||
|-|-|-|
|![Busqueda](gui-screenshots/gui-busqueda.png)|![Exito](gui-screenshots/gui-edici%C3%B3n-huesped-exito.png)|![Exito](gui-screenshots/gui-edici%C3%B3n-reserva-exito.png)|
|![Edición huesped](gui-screenshots/gui-editar-huesped.png)|![Edición reserva](gui-screenshots/gui-editar-reserva.png)|![Error consulta](gui-screenshots/gui-error-registro.png)|
|![Login](gui-screenshots/gui-login.png)|![Menu principal](gui-screenshots/gui-menu-principal.png)|![Menu Usuario](gui-screenshots/gui-menu-usuario.png)|
|![Exito registro](gui-screenshots/gui-registro-exitoso.png)|![Regitro huesped](gui-screenshots/gui-registro-huesped.png)|![Registro Reserva](gui-screenshots/gui-reservas-view.png)|

## &#128187; Indicaciones para ejecución desde IDE

Antes de intentar correr el programa, se requiere ejecutar el script sql que viene en la carpeta `/db` de este repositorio.

Para ejecutar el código es necesario asignar las variables de entorno en tu IDE:
- MYSQL_USERNAME=TuUsuarioMySql
- MYSQL_PASSWORD=TuContraseñaMySql

El programa inicia su ejecución desde la clase `App` con el método main.
