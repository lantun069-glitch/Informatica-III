# 🎓 Informática III - Trabajos Prácticos

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Status](https://img.shields.io/badge/Status-Active-green.svg)]()

**Universidad:** Instituto Universitario Aeronáutico  
**Materia:** Informática III  
**Estudiantes:** Lucas Santiago Said Antun & Federico Fernández  
**Año:** 2025

## 📚 Descripción

Este repositorio contiene la implementación completa y profesionalizada de todos los trabajos prácticos de la materia Informática III. El proyecto ha sido completamente refactorizado, documentado y organizado siguiendo las mejores prácticas de desarrollo de software.

## 🚀 Ejecución Rápida

```bash
# Clonar el repositorio
git clone <repository-url>
cd Informatica-III

# Dar permisos de ejecución
chmod +x ejecutar.sh

# Ejecutar el sistema
./ejecutar.sh
```

## 📁 Estructura del Proyecto

```
Informatica-III/
├── 📂 src/edu/informatica3/lucas_antun/
│   ├── 📂 practico01/          # Sistema de Gestión de Tareas (POO)
│   │   ├── � GestorTareas.java
│   │   ├── 📄 MainTareas.java
│   │   └── 📄 Tarea.java
│   ├── �📂 practico02/          # Ejercicios de Recursividad
│   │   └── 📄 EjerciciosRecursividad.java
│   ├── 📂 practico03/          # Sistema de Pizzería (Algoritmos)
│   │   ├── � AlgoritmosOrdenamiento.java
│   │   ├── 📄 MainPizzeria.java
│   │   ├── 📄 MedidorTiempos.java
│   │   ├── 📄 Pedido.java
│   │   └── 📄 SistemaPizzeria.java
│   ├── �📂 practico04/          # Estructuras de Datos (Pilas y Colas)
│   │   ├── 📄 DemostracionEstructuras.java
│   │   └── 📄 PilaArreglo.java
│   ├── 📂 practico05/          # Árboles AVL (Auto-balanceados)
│   │   ├── 📄 ArbolAVL.java
│   │   ├── 📄 DemostracionAVL.java
│   │   ├── 📄 NodoAVL.java
│   │   └── 📄 ResultadoVerificacionAVL.java
│   ├── 📂 practico06/          # Árboles Rojo-Negro (Red-Black Trees)
│   │   ├── � ArbolRojoNegro.java
│   │   ├── 📄 CasoRebalanceo.java
│   │   ├── 📄 Color.java
│   │   ├── 📄 DemostracionRojoNegro.java
│   │   └── 📄 NodoRojoNegro.java
│   └── 📂 practico_integrador/ # Sistema de Gestión de Turnos Médicos
│       ├── 📄 Medico.java
│       ├── 📄 Paciente.java
│       ├── 📄 SistemaGestionTurnos.java
│       ├── 📄 Turno.java
│       ├── 📄 medicos.csv
│       ├── 📄 pacientes.csv
│       └── 📄 turnos.csv
├── 📂 bin/                     # Archivos compilados (.class)
├── 🚀 ejecutar.sh             # Script de ejecución principal
├── 📖 README.md               # Este archivo
└── 📄 LICENSE                 # Licencia MIT
```

## 💻 Prácticos Implementados

### 📋 Práctico 1: Sistema de Gestión de Tareas (POO)
**Estado:** ✅ Completo

Implementación de un sistema completo de gestión de tareas usando Programación Orientada a Objetos.

**Características:**
- ✨ Gestión completa de tareas (CRUD)
- 💾 Persistencia en archivos
- 🔍 Búsqueda y filtrado avanzado
- 📊 Estadísticas y reportes
- 🎯 Interfaz de usuario intuitiva

**Clases principales:**
- `Tarea`: Entidad principal con validaciones robustas
- `GestorTareas`: Lógica de negocio y persistencia
- `MainTareas`: Interfaz de usuario interactiva

### 🔄 Práctico 2: Ejercicios de Recursividad
**Estado:** ✅ Completo

Colección completa de algoritmos recursivos con análisis de complejidad.

**Ejercicios incluidos:**
- 🧮 **Factorial**: Cálculo iterativo y recursivo
- 🌀 **Fibonacci**: Múltiples implementaciones optimizadas
- 🔤 **Palíndromos**: Verificación recursiva de cadenas
- 🔢 **Operaciones con dígitos**: Suma, conteo, inversión
- 📈 **Potenciación**: Algoritmo de exponenciación rápida
- 🎯 **Torres de Hanoi**: Solución clásica recursiva
- 📊 **Análisis de complejidad**: Big O para cada algoritmo

### 🍕 Práctico 3: Sistema de Pizzería (Algoritmos de Ordenamiento)
**Estado:** ✅ Completo

Sistema completo de gestión de pizzería con implementación y análisis de algoritmos de ordenamiento.

**Características:**
- 🏪 **Gestión de pedidos**: Sistema completo de pedidos
- ⚡ **Algoritmos de ordenamiento**:
  - Insertion Sort (estable, in-place)
  - Shell Sort (optimizado con secuencia de Knuth)
  - Quick Sort (partición de Lomuto, aleatorización)
- 📊 **Análisis de rendimiento**: Medición de tiempos y comparaciones
- 📈 **Visualización**: Representación clara de resultados
- 🎲 **Generación de datos**: Casos de prueba automatizados

**Clases principales:**
- `Pedido`: Entidad con múltiples criterios de ordenamiento
- `SistemaPizzeria`: Gestión integral del negocio
- `AlgoritmosOrdenamiento`: Implementaciones optimizadas
- `MedidorTiempos`: Análisis de rendimiento profesional

### 📚 Práctico 4: Estructuras de Datos (Pilas y Colas)
**Estado:** ✅ Completo

Implementación completa de estructuras de datos fundamentales con aplicaciones prácticas.

**Estructuras implementadas:**
- 📚 **Pila con Arreglo** (`PilaArreglo<T>`):
  - Implementación genérica completa
  - Operaciones: push, pop, top, search
  - Validaciones robustas y manejo de errores
  - Utilidades: isEmpty, isFull, size, clear, clone

**Aplicaciones demostradas:**
- ✅ **Verificación de paréntesis balanceados**
- 🔄 **Conversión de notación infija a postfija**
- 🧮 **Evaluación de expresiones postfijas**
- 🌐 **Simulador de historial de navegación web**

**Características técnicas:**
- 🔧 **Genericidad**: Soporte para cualquier tipo de objeto
- 📖 **Documentación JavaDoc**: Completamente documentado
- 🧪 **Casos de prueba**: Demostraciones exhaustivas
- ⚡ **Rendimiento**: Operaciones O(1) para la mayoría

### 🌳 Práctico 5: Árboles AVL (Auto-balanceados)
**Estado:** ✅ Completo

Implementación completa y profesional de árboles AVL auto-balanceados con todas las operaciones fundamentales.

**Características:**
- 🔄 **Rotaciones completas**: Simples (LL, RR) y dobles (LR, RL)
- ⚖️ **Auto-balanceado**: Mantenimiento automático del factor de balance
- 🔍 **Operaciones eficientes**: Búsqueda, inserción y eliminación O(log n)
- 📊 **Análisis de altura**: Cálculo y verificación de propiedades AVL
- 🧭 **Navegación**: Predecesor, sucesor, mínimo, máximo
- � **Consultas por rango**: Búsqueda eficiente en intervalos
- 🎯 **10 ejercicios demostrativos**: Casos de uso completos

**Clases principales:**
- `NodoAVL<K, V>`: Nodo genérico con factor de balance
- `ArbolAVL<K, V>`: Implementación completa del árbol
- `ResultadoVerificacionAVL`: Validación de propiedades
- `DemostracionAVL`: 10 ejercicios educativos interactivos

### 🔴 Práctico 6: Árboles Rojo-Negro (Red-Black Trees)
**Estado:** ✅ Completo

Implementación profesional de árboles Rojo-Negro con verificación completa de propiedades y operaciones avanzadas.

**Características:**
- 🎨 **Sistema de colores**: Enum type-safe para colores ROJO/NEGRO
- 🔄 **Rotaciones automáticas**: Mantenimiento de propiedades RB
- ✅ **Verificación de propiedades**: Las 5 propiedades fundamentales
- 🧭 **Navegación completa**: Predecesor/sucesor eficientes
- 📊 **Análisis de altura negra**: Verificación de consistencia
- 🔍 **Consultas por rango**: Búsqueda eficiente en intervalos
- 🎯 **10 ejercicios demostrativos**: Desde básico hasta test de estrés

**Clases principales:**
- `Color`: Enum type-safe para colores del árbol
- `CasoRebalanceo`: Clasificación de casos de rebalanceo
- `NodoRojoNegro<K, V>`: Nodo con propiedades de color
- `ArbolRojoNegro<K, V>`: Implementación completa con todas las operaciones
- `DemostracionRojoNegro`: 10 ejercicios educativos interactivos

**Propiedades Red-Black verificadas:**
1. ✅ Todo nodo es rojo o negro
2. ✅ La raíz es siempre negra
3. ✅ Las hojas (NIL) son negras
4. ✅ Sin nodos rojos consecutivos
5. ✅ Misma altura negra en todos los caminos

### 🏥 Práctico Integrador: Sistema de Gestión de Turnos Médicos
**Estado:** ✅ Completo

Sistema integral de gestión de turnos médicos que combina múltiples conceptos estudiados en la materia, incluyendo manejo de archivos CSV, estructuras de datos, validaciones y programación orientada a objetos.

**Características:**
- 👥 **Gestión de entidades**: Pacientes, médicos y turnos médicos
- 📄 **Carga desde CSV**: Importación automática de datos desde archivos
- ✅ **Validaciones robustas**: 
  - Turnos duplicados por ID
  - Fechas pasadas automáticamente rechazadas
  - Verificación de existencia de pacientes y médicos
  - Validación de duración de turnos
- 📊 **Reportes detallados**: Estadísticas de carga y validación
- 🏗️ **Arquitectura limpia**: Separación clara de responsabilidades

**Clases principales:**
- `Paciente`: Entidad básica con DNI y nombre
- `Medico`: Entidad con matrícula, nombre y especialidad
- `Turno`: Gestión completa de turnos con fecha/hora, duración y motivo
- `SistemaGestionTurnos`: Clase principal con lógica de negocio

**Funcionalidades de validación:**
- 🚫 **Rechazo automático** de turnos con fechas pasadas
- 🔍 **Detección de duplicados** por ID de turno
- ⚡ **Validación de referencias** entre entidades
- 📏 **Control de duración** de turnos (debe ser > 0)

**Archivos CSV incluidos:**
- `pacientes.csv`: 7 pacientes de prueba
- `medicos.csv`: 5 médicos con diferentes especialidades
- `turnos.csv`: 10 turnos con casos de validación diversos

## 🛠️ Tecnologías y Herramientas

- **Lenguaje:** Java 17+ (compatible con versiones superiores)
- **Paradigma:** Programación Orientada a Objetos
- **Documentación:** JavaDoc estándar
- **Build System:** Compilación manual con scripts automatizados
- **Testing:** Casos de prueba integrados y demostraciones

## 🎯 Características del Proyecto

### 📐 Arquitectura
- **Organización por paquetes:** Estructura Maven estándar
- **Separación de responsabilidades:** Una clase, una responsabilidad
- **Principios SOLID:** Aplicados consistentemente
- **Patrones de diseño:** Factory, Strategy, Template Method

### 📚 Documentación
- **JavaDoc completo:** Todas las clases y métodos documentados
- **Ejemplos de uso:** Código de ejemplo en cada método
- **Análisis de complejidad:** Big O notation para algoritmos
- **Comentarios explicativos:** Lógica compleja explicada

### 🔧 Calidad de Código
- **Validación de entrada:** Manejo robusto de errores
- **Excepciones personalizadas:** Mensajes de error descriptivos
- **Constantes nombradas:** Sin números mágicos
- **Nomenclatura consistente:** Convenciones Java estándar

### 🎨 Experiencia de Usuario
- **Interfaz intuitiva:** Menús claros y navegación simple
- **Retroalimentación visual:** Indicadores de progreso y estado
- **Manejo de errores graceful:** Mensajes informativos
- **Salida formateada:** Presentación profesional de resultados

## 🚀 Uso del Sistema

### Ejecución Interactiva
```bash
./ejecutar.sh
```

### Compilación Manual
```bash
# Compilar todos los prácticos
javac -d bin -cp bin src/edu/informatica3/lucas_antun/*/*.java

# Ejecutar un práctico específico
java -cp bin src.edu.informatica3.lucas_antun.practico01.MainTareas
```

### Ejecución Individual
```bash
# Práctico 1: Sistema de Tareas
java -cp bin src.edu.informatica3.lucas_antun.practico01.MainTareas

# Práctico 2: Recursividad
java -cp bin src.edu.informatica3.lucas_antun.practico02.EjerciciosRecursividad

# Práctico 3: Sistema de Pizzería
java -cp bin src.edu.informatica3.lucas_antun.practico03.MainPizzeria

# Práctico 4: Estructuras de Datos
java -cp bin src.edu.informatica3.lucas_antun.practico04.DemostracionEstructuras

# Práctico 5: Árboles AVL
java -cp bin src.edu.informatica3.lucas_antun.practico05.DemostracionAVL

# Práctico 6: Árboles Rojo-Negro
java -cp bin src.edu.informatica3.lucas_antun.practico06.DemostracionRojoNegro

# Práctico Integrador: Sistema de Gestión de Turnos Médicos
cd src/edu/informatica3/lucas_antun/practico_integrador
java -cp "/home/lucas/Desktop/Informatica-III/src" edu.informatica3.lucas_antun.practico_integrador.SistemaGestionTurnos
```

## 📊 Métricas del Proyecto

- **📄 Líneas de código:** 7,900+ líneas
- **🏗️ Clases implementadas:** 24 archivos Java
- **📝 Métodos documentados:** 170+
- **🧪 Casos de prueba:** 80+ ejercicios educativos
- **⏱️ Algoritmos analizados:** 25+
- **🌳 Estructuras de datos:** 6 implementaciones completas
- **📚 Prácticos completados:** 7/7 (100%)

## 🎓 Objetivos Académicos Cumplidos

### Conceptos de Programación
- ✅ **Programación Orientada a Objetos**
- ✅ **Estructuras de Datos Fundamentales**
- ✅ **Algoritmos de Ordenamiento**
- ✅ **Recursividad y Análisis de Complejidad**
- ✅ **Árboles Auto-balanceados (AVL y Red-Black)**
- ✅ **Manejo de Archivos y Persistencia**

### Competencias Profesionales
- ✅ **Documentación Técnica Profesional**
- ✅ **Manejo de Errores y Validaciones**
- ✅ **Organización y Estructura de Proyectos**
- ✅ **Testing y Validación de Software**
- ✅ **Interfaces de Usuario Intuitivas**

## 🤝 Contribuciones

Este es un proyecto académico personal. Sin embargo, sugerencias y mejoras son bienvenidas a través de issues.

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.

## 👨‍💻 Autores

**Lucas Santiago Said Antun**
- 🎓 Estudiante de Informática III
- 🏫 Instituto Universitario Aeronáutico
- 📧 lantun069@alumnos.iua.edu.ar

**Federico Fernández**
- 🎓 Estudiante de Informática III  
- 🏫 Instituto Universitario Aeronáutico
- 📧 ffernandez576@alumnos.iua.edu.ar

---

<div align="center">

**⭐ Si este proyecto te resulta útil, no olvides darle una estrella ⭐**

*Desarrollado con 💙 para la comunidad académica*

</div>