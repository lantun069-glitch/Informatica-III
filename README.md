# Informática III - Trabajos Prácticos

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

**Universidad:** Instituto Universitario Aeronáutico  
**Materia:** Informática III  
**Estudiantes:** Lucas Santiago Said Antun & Federico Fernández  
**Año:** 2025

## Descripción

Implementación completa de los trabajos prácticos de Informática III, incluyendo estructuras de datos, algoritmos de ordenamiento, árboles balanceados y un sistema integrador de gestión de turnos médicos.

## Ejecución Rápida

```bash
# Ejecutar el menú interactivo
./ejecutar.sh
```

## Estructura del Proyecto

```
Informatica-III/
├── src/edu/informatica3/lucas_antun/
│   ├── practico01/              # Sistema de Gestión de Tareas
│   │   ├── main/
│   │   ├── modelo/
│   │   └── gestor/
│   ├── practico02/              # Ejercicios de Recursividad
│   ├── practico03/              # Sistema de Pizzería (Algoritmos)
│   │   ├── main/
│   │   ├── modelo/
│   │   └── algoritmos/
│   ├── practico04/              # Pilas y Colas
│   │   ├── main/
│   │   └── estructuras/
│   ├── practico05/              # Árboles AVL
│   │   ├── main/
│   │   ├── estructuras/
│   │   ├── nodos/
│   │   └── utils/
│   ├── practico06/              # Árboles Rojo-Negro
│   │   ├── main/
│   │   ├── estructuras/
│   │   ├── nodos/
│   │   └── enums/
│   └── practico_integrador/     # Sistema de Gestión de Turnos Médicos
│       ├── main/
│       ├── modelo/
│       ├── estructuras/
│       ├── nodos/
│       ├── enums/
│       ├── utils/
│       └── data/                # Archivos CSV de datos
├── bin/                         # Archivos compilados
├── ejecutar.sh                  # Script de ejecución
└── README.md
```

## Prácticos Implementados

### 1. Sistema de Gestión de Tareas (POO)
- CRUD completo de tareas
- Persistencia en archivos
- Búsqueda y filtrado

### 2. Ejercicios de Recursividad
- 8 ejercicios: factorial, fibonacci, palíndromos, torres de Hanoi, etc.
- Análisis de complejidad temporal

### 3. Sistema de Pizzería (Algoritmos de Ordenamiento)
- Insertion Sort, Shell Sort, Quick Sort
- Medición y comparación de rendimiento
- Verificadores de ordenamiento

### 4. Estructuras de Datos (Pilas y Colas)
- Implementación con arreglos
- Cola circular
- 8 ejercicios prácticos

### 5. Árboles AVL (Auto-balanceados)
- Árbol binario auto-balanceado
- Rotaciones simples y dobles
- Verificación de propiedades AVL

### 6. Árboles Rojo-Negro (Red-Black Trees)
- Implementación completa
- Rebalanceo con casos
- Visualización de rotaciones

### 7. Práctico Integrador: Sistema de Gestión de Turnos Médicos
Sistema completo que integra todas las estructuras de datos:

1. **Carga de datos desde CSV** (medicos, pacientes, turnos)
2. **Agenda por médico** con AVL Tree
3. **Búsqueda de huecos libres** en agenda
4. **Sala de espera** con cola circular
5. **Recordatorios** con planificador de prioridad
6. **Índice de pacientes** con Hash Table
7. **Consolidación de agendas** (merge)
8. **Reportes** con algoritmos de ordenamiento
9. **Auditoría** con Undo/Redo (Pilas)
10. **Planificador de quirófano** con heaps (Min y Max)

## Compilación y Ejecución

### Opción 1: Script automatizado (recomendado)
```bash
./ejecutar.sh
```

El script ofrece un menú interactivo con opciones para:
- Ejecutar cada práctico individualmente
- Compilar todos los prácticos
- Ver ayuda y descripción de cada ejercicio

### Opción 2: Compilación manual
```bash
# Compilar todo
javac -d bin -encoding UTF-8 src/edu/informatica3/lucas_antun/**/*.java

# Ejecutar un práctico específico
java -cp bin edu.informatica3.lucas_antun.practico01.main.MainTareas
java -cp bin edu.informatica3.lucas_antun.practico_integrador.main.SistemaGestionTurnosMedicos
```
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
**Estado:**  Completo (10/10 ejercicios implementados)

Sistema profesional y completo de gestión de turnos médicos que integra **todas las estructuras de datos** estudiadas en la materia: AVL Trees, Hash Tables, Min-Heaps, Colas Circulares y Pilas. Incluye 10 ejercicios independientes con funcionalidades avanzadas.

#### Ejercicios Implementados:

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

**9️⃣ Auditoría y Undo/Redo (Pilas)** 
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

#### Estructuras de Datos Implementadas:

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

**Sistema Undo/Redo (Pilas)** 
- Stack<OperacionHistorial> para historial
- Clase interna con enum TipoOperacion
- Soporte para AGREGAR, CANCELAR, REPROGRAMAR
- Manejo de fechas anteriores para reprogramaciones

#### Archivos de Datos:

- `data/pacientes_data.csv`: 30 pacientes de prueba
- `data/medicos_data.csv`: 5 médicos con especialidades variadas
- `data/turnos_data.csv`: 123 turnos con validaciones diversas

## Características del Proyecto

### Arquitectura
- **Organización por paquetes:** Estructura estándar
- **Separación de responsabilidades:** Una clase, una responsabilidad
- **Principios SOLID:** Aplicados consistentemente

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

## Requisitos

- Java JDK 17 o superior
- Sistema operativo: Linux, macOS o Windows
- Terminal con soporte para scripts bash (en Windows usar Git Bash o WSL)

## Características Técnicas

- **Sin clases internas**: Todo el código usa clases independientes
- **Organización modular**: Cada práctico organizado por responsabilidades (main, modelo, estructuras, etc.)
- **Documentación Javadoc**: Todas las clases principales documentadas
- **Validaciones robustas**: Manejo de errores y casos edge
- **Código limpio**: Siguiendo convenciones de Java

## Tecnologías y Conceptos

- Programación Orientada a Objetos
- Estructuras de Datos (Pilas, Colas, Listas, Árboles)
- Algoritmos de Ordenamiento (O(n²), O(n log n))
- Árboles Balanceados (AVL, Red-Black)
- Hash Tables con encadenamiento
- Heaps (Min-Heap, Max-Heap)
- Recursividad
- Análisis de complejidad temporal

## Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para detalles.

## Autores

**Lucas Santiago Said Antun**  
📧 lantun069@alumnos.iua.edu.ar  
🎓 Instituto Universitario Aeronáutico

**Federico Fernández**  
📧 ffernandez576@alumnos.iua.edu.ar  
🎓 Instituto Universitario Aeronáutico

---

<div align="center">

**Informática III - 2025**  
*Instituto Universitario Aeronáutico*

</div>