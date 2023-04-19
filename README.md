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
          DOUBLE valor
          VARCHAR forma_pago
          BIGINT huesped_id
     }

```

## Capturas

||||
|-|-|-|
|![](gui-screenshots/gui-menu-principal.png)|![](gui-screenshots/gui-login.png)|![](gui-screenshots/gui-menu-usuario.png)|
|![](gui-screenshots/gui-busqueda.png)|![](gui-screenshots/gui-reservas-view.png)|![](gui-screenshots/gui-registro-huesped.png)|
|![](gui-screenshots/gui-exito.png)|
