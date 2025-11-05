# Informática III - Trabajos Prácticos

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Status](https://img.shields.io/badge/Status-Active-green.svg)]()

**Universidad:** Instituto Universitario Aeronáutico  
**Materia:** Informática III  
**Estudiantes:** Lucas Santiago Said Antun & Federico Fernández  
**Año:** 2025

## Descripción

Este repositorio contiene la implementación completa y profesionalizada de todos los trabajos prácticos de la materia Informática III. El proyecto ha sido completamente refactorizado, documentado y organizado siguiendo las mejores prácticas de desarrollo de software.

## Ejecución Rápida

```bash
# Clonar el repositorio
git clone <repository-url>
cd Informatica-III

# Dar permisos de ejecución
chmod +x ejecutar.sh

# Ejecutar el sistema
./ejecutar.sh
```

## Estructura del Proyecto

```
Informatica-III/
├── src/edu/informatica3/lucas_antun/
│   ├── practico01/          # Sistema de Gestión de Tareas (POO)
│   │   ├── GestorTareas.java
│   │   ├── MainTareas.java
│   │   └── Tarea.java
│   ├── practico02/          # Ejercicios de Recursividad
│   │   └── EjerciciosRecursividad.java
│   ├── practico03/          # Sistema de Pizzería (Algoritmos)
│   │   ├── AlgoritmosOrdenamiento.java
│   │   ├── MainPizzeria.java
│   │   ├── MedidorTiempos.java
│   │   ├── Pedido.java
│   │   └── SistemaPizzeria.java
│   ├── practico04/          # Estructuras de Datos (Pilas y Colas)
│   │   ├── DemostracionEstructuras.java
│   │   └── PilaArreglo.java
│   ├── practico05/          # Árboles AVL (Auto-balanceados)
│   │   ├── ArbolAVL.java
│   │   ├── DemostracionAVL.java
│   │   ├── NodoAVL.java
│   │   └── ResultadoVerificacionAVL.java
│   ├── practico06/          # Árboles Rojo-Negro (Red-Black Trees)
│   │   ├── ArbolRojoNegro.java
│   │   ├── CasoRebalanceo.java
│   │   ├── Color.java
│   │   ├── DemostracionRojoNegro.java
│   │   └── NodoRojoNegro.java
│   └── practico_integrador/ # Sistema de Gestión de Turnos Médicos
│       ├── AgendaMedico.java
│       ├── MapaPacientes.java
│       ├── Medico.java
│       ├── Paciente.java
│       ├── PlanificadorQuirofano.java
│       ├── PlanificadorRecordatorios.java
│       ├── Recordatorio.java
│       ├── SalaEspera.java
│       ├── SistemaGestionTurnosMedicos.java
│       ├── SolicitudCirugia.java
│       ├── Turno.java
│       ├── data/
│       │   ├── medicos_data.csv
│       │   ├── pacientes_data.csv
│       │   └── turnos_data.csv
│       └── README.md
├── bin/                     # Archivos compilados (.class)
├── ejecutar.sh             # Script de ejecución principal
├── README.md               # Este archivo
└── LICENSE                 # Licencia MIT
```

## Prácticos Implementados

### Práctico 1: Sistema de Gestión de Tareas (POO)
**Estado:** Completo

Implementación de un sistema completo de gestión de tareas usando Programación Orientada a Objetos.

**Características:**
- Gestión completa de tareas (CRUD)
- Persistencia en archivos
- Búsqueda y filtrado avanzado
- Estadísticas y reportes
- Interfaz de usuario intuitiva

**Clases principales:**
- `Tarea`: Entidad principal con validaciones robustas
- `GestorTareas`: Lógica de negocio y persistencia
- `MainTareas`: Interfaz de usuario interactiva

### Práctico 2: Ejercicios de Recursividad
**Estado:** Completo

Colección completa de algoritmos recursivos con análisis de complejidad.

**Ejercicios incluidos:**
- **Factorial**: Cálculo iterativo y recursivo
- **Fibonacci**: Múltiples implementaciones optimizadas
- **Palíndromos**: Verificación recursiva de cadenas
- **Operaciones con dígitos**: Suma, conteo, inversión
- **Potenciación**: Algoritmo de exponenciación rápida
- **Torres de Hanoi**: Solución clásica recursiva
- **Análisis de complejidad**: Big O para cada algoritmo

### Práctico 3: Sistema de Pizzería (Algoritmos de Ordenamiento)
**Estado:** Completo

Sistema completo de gestión de pizzería con implementación y análisis de algoritmos de ordenamiento.

**Características:**
- **Gestión de pedidos**: Sistema completo de pedidos
- **Algoritmos de ordenamiento**:
  - Insertion Sort (estable, in-place)
  - Shell Sort (optimizado con secuencia de Knuth)
  - Quick Sort (partición de Lomuto, aleatorización)
- **Análisis de rendimiento**: Medición de tiempos y comparaciones
- **Visualización**: Representación clara de resultados
- **Generación de datos**: Casos de prueba automatizados

**Clases principales:**
- `Pedido`: Entidad con múltiples criterios de ordenamiento
- `SistemaPizzeria`: Gestión integral del negocio
- `AlgoritmosOrdenamiento`: Implementaciones optimizadas
- `MedidorTiempos`: Análisis de rendimiento profesional

### Práctico 4: Estructuras de Datos (Pilas y Colas)
**Estado:** Completo

Implementación completa de estructuras de datos fundamentales con aplicaciones prácticas.

**Estructuras implementadas:**
- **Pila con Arreglo** (`PilaArreglo<T>`):
  - Implementación genérica completa
  - Operaciones: push, pop, top, search
  - Validaciones robustas y manejo de errores
  - Utilidades: isEmpty, isFull, size, clear, clone

**Aplicaciones demostradas:**
- Verificación de paréntesis balanceados
- Conversión de notación infija a postfija
- Evaluación de expresiones postfijas
- Simulador de historial de navegación web

**Características técnicas:**
- Genericidad: Soporte para cualquier tipo de objeto
- Documentación JavaDoc: Completamente documentado
- Casos de prueba: Demostraciones exhaustivas
- Rendimiento: Operaciones O(1) para la mayoría

### Práctico 5: Árboles AVL (Auto-balanceados)
**Estado:** Completo

Implementación completa y profesional de árboles AVL auto-balanceados con todas las operaciones fundamentales.

**Características:**
- Rotaciones completas: Simples (LL, RR) y dobles (LR, RL)
- Auto-balanceado: Mantenimiento automático del factor de balance
- Operaciones eficientes: Búsqueda, inserción y eliminación O(log n)
- Análisis de altura: Cálculo y verificación de propiedades AVL
- Navegación: Predecesor, sucesor, mínimo, máximo
- Consultas por rango: Búsqueda eficiente en intervalos
- 10 ejercicios demostrativos: Casos de uso completos

**Clases principales:**
- `NodoAVL<K, V>`: Nodo genérico con factor de balance
- `ArbolAVL<K, V>`: Implementación completa del árbol
- `ResultadoVerificacionAVL`: Validación de propiedades
- `DemostracionAVL`: 10 ejercicios educativos interactivos

### Práctico 6: Árboles Rojo-Negro (Red-Black Trees)
**Estado:** Completo

Implementación profesional de árboles Rojo-Negro con verificación completa de propiedades y operaciones avanzadas.

**Características:**
- Sistema de colores: Enum type-safe para colores ROJO/NEGRO
- Rotaciones automáticas: Mantenimiento de propiedades RB
- Verificación de propiedades: Las 5 propiedades fundamentales
- Navegación completa: Predecesor/sucesor eficientes
- Análisis de altura negra: Verificación de consistencia
- Consultas por rango: Búsqueda eficiente en intervalos
- 10 ejercicios demostrativos: Desde básico hasta test de estrés

**Clases principales:**
- `Color`: Enum type-safe para colores del árbol
- `CasoRebalanceo`: Clasificación de casos de rebalanceo
- `NodoRojoNegro<K, V>`: Nodo con propiedades de color
- `ArbolRojoNegro<K, V>`: Implementación completa con todas las operaciones
- `DemostracionRojoNegro`: 10 ejercicios educativos interactivos

**Propiedades Red-Black verificadas:**
1. Todo nodo es rojo o negro
2. La raíz es siempre negra
3. Las hojas (NIL) son negras
4. Sin nodos rojos consecutivos
5. Misma altura negra en todos los caminos

### Práctico Integrador: Sistema de Gestión de Turnos Médicos
**Estado:** ✅ Completo (10/10 ejercicios implementados)

Sistema profesional y completo de gestión de turnos médicos que integra **todas las estructuras de datos** estudiadas en la materia: AVL Trees, Hash Tables, Min-Heaps, Colas Circulares y Pilas. Incluye 10 ejercicios independientes con funcionalidades avanzadas.

#### 🎯 Ejercicios Implementados:

**1️⃣ Carga Inicial desde CSV con Validaciones**
- Importación automática de datos desde archivos CSV
- Validaciones robustas (duplicados, fechas pasadas, duraciones inválidas)
- Rechazo automático de turnos con fechas pasadas
- Detección de IDs duplicados
- Reportes detallados de carga

**2️⃣ Agenda por Médico (AVL Tree)**
- Árbol AVL auto-balanceado por fecha/hora
- Búsqueda eficiente O(log n)
- Detección automática de solapamientos
- Navegación por predecesor/sucesor
- Visualización ordenada de turnos

**3️⃣ Búsqueda de Huecos Libres**
- Algoritmo inteligente de búsqueda de slots disponibles
- Considera duración del turno y horarios laborales
- Sugiere próximas fechas disponibles
- Optimización O(n) donde n = turnos existentes

**4️⃣ Sala de Espera (Cola Circular)**
- Implementación propia de cola circular con capacidad fija
- Control de overflow (pacientes rechazados)
- FIFO estricto para equidad
- Simulación de atención por médico
- Operaciones O(1) para enqueue/dequeue

**5️⃣ Planificador de Recordatorios (Min-Heap)**
- Min-Heap por fecha de recordatorio
- Priorización automática por urgencia
- Procesamiento eficiente de recordatorios vencidos
- Operaciones O(log n) para insert/extractMin
- Visualización de recordatorios pendientes

**6️⃣ Índice de Pacientes (Hash Table)**
- Implementación propia con chaining
- Función hash personalizada
- Rehashing automático (factor de carga > 0.75)
- Búsqueda O(1) en promedio
- Estadísticas de colisiones y distribución

**7️⃣ Consolidación de Agendas (Merge)**
- Algoritmo de merge O(n+m) para unir agendas
- Detección inteligente de conflictos
- Resolución de duplicados
- Preserva orden temporal
- Validación de solapamientos

**8️⃣ Reportes con Algoritmos de Ordenamiento**
- Insertion Sort (estable, O(n²))
- Shell Sort con secuencia de Knuth
- Quick Sort con pivote aleatorizado
- Comparación de rendimiento
- Múltiples criterios de ordenamiento

**9️⃣ Auditoría y Undo/Redo (Pilas)** ⭐ **NUEVO**
- Sistema interactivo con menú completo
- Operaciones: Agregar, Cancelar, Reprogramar turnos
- Dos pilas LIFO (Undo/Redo)
- Deshacer/Rehacer multi-nivel
- Redo bloqueado después de nueva acción
- Invariantes preservados (sin solapamientos)
- Historial completo de operaciones
- Complejidad: O(1) para push/pop

**🔟 Planificador de Quirófano (Heaps + Top-K)**
- Min-Heap para solicitudes de cirugía
- Asignación eficiente a quirófanos disponibles
- Algoritmo Top-K para médicos más ocupados
- Optimización de recursos quirúrgicos
- Estadísticas de utilización

#### 📦 Estructuras de Datos Implementadas:

**AgendaMedico (AVL Tree)**
- Árbol AVL auto-balanceado con clave compuesta (fecha/hora + ID)
- Rotaciones completas (LL, RR, LR, RL)
- Factor de balance automático
- Búsqueda de predecesor/sucesor O(log n)
- Consultas por rango de fechas

**MapaPacientes (Hash Table)**
- Tabla hash con chaining (listas enlazadas)
- Función hash: `(dni.hashCode() & 0x7FFFFFFF) % M`
- Rehashing dinámico al superar factor de carga 0.75
- Operaciones O(1) en promedio
- Estadísticas de colisiones

**SalaEspera (Cola Circular)**
- Array circular con capacidad fija
- Indicadores `front` y `rear`
- Control de overflow y underflow
- Operaciones O(1) constante
- FIFO estricto

**PlanificadorRecordatorios (Min-Heap)**
- Heap binario mínimo por fecha
- Percolate-up y percolate-down
- Operaciones O(log n) para insert/extract
- Array dinámico con redimensionamiento

**PlanificadorQuirofano (Min-Heap + Max-Heap)**
- Min-Heap para solicitudes urgentes
- Max-Heap para ranking de médicos
- Asignación óptima de recursos
- Top-K con heap de tamaño K

**Sistema Undo/Redo (Pilas)** ⭐
- Stack<OperacionHistorial> para historial
- Clase interna con enum TipoOperacion
- Soporte para AGREGAR, CANCELAR, REPROGRAMAR
- Manejo de fechas anteriores para reprogramaciones

#### 🔧 Clases Principales:

- `SistemaGestionTurnosMedicos`: Sistema principal con menú de 10 opciones
- `AgendaMedico`: AVL Tree para gestión de turnos por médico
- `MapaPacientes`: Hash Table propia para índice de pacientes
- `SalaEspera`: Cola circular para gestión de sala de espera
- `PlanificadorRecordatorios`: Min-Heap para recordatorios
- `PlanificadorQuirofano`: Heaps para planificación quirúrgica
- `Turno`: Entidad con fecha/hora, duración, solapamiento
- `Paciente`: Entidad con DNI, nombre y datos de contacto
- `Medico`: Entidad con matrícula, especialidad y agenda
- `Recordatorio`: Entidad para sistema de notificaciones
- `SolicitudCirugia`: Entidad para planificación quirúrgica
- `OperacionHistorial`: Clase interna para Undo/Redo

#### 📊 Archivos de Datos:

- `data/pacientes_data.csv`: 30 pacientes de prueba
- `data/medicos_data.csv`: 5 médicos con especialidades variadas
- `data/turnos_data.csv`: 123 turnos con validaciones diversas

## Tecnologías y Herramientas

- **Lenguaje:** Java 17+ (compatible con versiones superiores)
- **Paradigma:** Programación Orientada a Objetos
- **Documentación:** JavaDoc estándar
- **Build System:** Compilación manual con scripts automatizados
- **Testing:** Casos de prueba integrados y demostraciones

## Características del Proyecto

### Arquitectura
- **Organización por paquetes:** Estructura Maven estándar
- **Separación de responsabilidades:** Una clase, una responsabilidad
- **Principios SOLID:** Aplicados consistentemente
- **Patrones de diseño:** Factory, Strategy, Template Method

### Documentación
- **JavaDoc completo:** Todas las clases y métodos documentados
- **Ejemplos de uso:** Código de ejemplo en cada método
- **Análisis de complejidad:** Big O notation para algoritmos
- **Comentarios explicativos:** Lógica compleja explicada

### Calidad de Código
- **Validación de entrada:** Manejo robusto de errores
- **Excepciones personalizadas:** Mensajes de error descriptivos
- **Constantes nombradas:** Sin números mágicos
- **Nomenclatura consistente:** Convenciones Java estándar

### Experiencia de Usuario
- **Interfaz intuitiva:** Menús claros y navegación simple
- **Retroalimentación visual:** Indicadores de progreso y estado
- **Manejo de errores graceful:** Mensajes informativos
- **Salida formateada:** Presentación profesional de resultados

## Uso del Sistema

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
java -cp bin edu.informatica3.lucas_antun.practico_integrador.SistemaGestionTurnosMedicos
```

## Métricas del Proyecto

- **Líneas de código:** 11,000+ líneas
- **Clases implementadas:** 35 archivos Java
- **Métodos documentados:** 250+
- **Casos de prueba:** 90+ ejercicios educativos
- **Algoritmos analizados:** 30+
- **Estructuras de datos:** 10 implementaciones completas
- **Prácticos completados:** 7/7 (100%)
- **Ejercicios integrador:** 10/10 (100%)

## Objetivos Académicos Cumplidos

### Conceptos de Programación
- **Programación Orientada a Objetos**
- **Estructuras de Datos Fundamentales**
- **Algoritmos de Ordenamiento**
- **Recursividad y Análisis de Complejidad**
- **Árboles Auto-balanceados (AVL y Red-Black)**
- **Manejo de Archivos y Persistencia**

### Competencias Profesionales
- **Documentación Técnica Profesional**
- **Manejo de Errores y Validaciones**
- **Organización y Estructura de Proyectos**
- **Testing y Validación de Software**
- **Interfaces de Usuario Intuitivas**

## Contribuciones

Este es un proyecto académico personal. Sin embargo, sugerencias y mejoras son bienvenidas a través de issues.

## Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.

## Autores

**Lucas Santiago Said Antun**
- Estudiante de Informática III
- Instituto Universitario Aeronáutico
- lantun069@alumnos.iua.edu.ar

**Federico Fernández**
- Estudiante de Informática III
- Instituto Universitario Aeronáutico
- Colaborador del proyecto

---

<div align="center">

Si este proyecto te resulta útil, no olvides darle una estrella

Desarrollado para la comunidad académica

</div>