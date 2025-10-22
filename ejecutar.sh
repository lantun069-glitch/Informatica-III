#!/bin/bash

# Script para compilar y ejecutar los prácticos de Informática III
# Autores: Lucas Santiago Said Antun & Federico Fernández
# Versión: 1.0

# Colores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
PURPLE='\033[0;35m'
CYAN='\033[0;36m'
NC='\033[0m' # No Color

# Función para mostrar el menú principal
mostrar_menu() {
    clear
    echo "╔══════════════════════════════════════════════════════════════════════╗"
    echo "║                         INFORMÁTICA III                              ║"
    echo "║                    Trabajos Prácticos - 2025                         ║"
    echo "║         Lucas Santiago Said Antun & Federico Fernández               ║"
    echo "╠══════════════════════════════════════════════════════════════════════╣"
    echo "║                                                                      ║"
    echo "║  1) Práctico 1  - Sistema de Gestión de Tareas (POO)                 ║"
    echo "║  2) Práctico 2  - Ejercicios de Recursividad                         ║"
    echo "║  3) Práctico 3  - Sistema de Pizzería (Algoritmos de Ordenamiento)   ║"
    echo "║  4) Práctico 4  - Estructuras de Datos (Pilas y Colas)               ║"
    echo "║  5) Práctico 5  - Árboles AVL (Auto-balanceados)                     ║"
    echo "║  6) Práctico 6  - Árboles Rojo-Negro (Red-Black Trees)               ║"
    echo "║  7) Práctico Integrador - Sistema de Gestión de Turnos Médicos       ║"
    echo "║                                                                      ║"
    echo "║  c) Compilar todos los prácticos                                     ║"
    echo "║  h) Mostrar ayuda                                                    ║"
    echo "║  0) Salir                                                            ║"
    echo "║                                                                      ║"
    echo "╚══════════════════════════════════════════════════════════════════════╝"
}

# Función para compilar un práctico específico
compile_practico() {
    local practico=$1
    local name=$2
    
    echo -e "\n${YELLOW}🔨 Compilando $name...${NC}"
    
    # Crear directorio bin si no existe
    mkdir -p bin
    
    # Compilar
    if javac -d bin src/edu/informatica3/lucas_antun/$practico/*.java 2>/dev/null; then
        echo -e "${GREEN}✅ Compilación exitosa${NC}"
        return 0
    else
        echo -e "${RED}❌ Error en la compilación${NC}"
        return 1
    fi
}

# Función para compilar todos los prácticos
compile_all() {
    echo -e "\n${PURPLE}🔨 COMPILANDO TODOS LOS PRÁCTICOS${NC}"
    echo -e "${PURPLE}═══════════════════════════════════${NC}\n"
    
    mkdir -p bin
    
    local practicos=("practico01:Gestión de Tareas" "practico02:Recursividad" "practico03:Sistema de Pizzería" "practico04:Estructuras de Datos" "practico05:Árboles AVL" "practico06:Árboles Rojo-Negro" "practico_integrador:Sistema de Turnos Médicos")
    local success=0
    local total=0
    
    for entry in "${practicos[@]}"; do
        local practico="${entry%%:*}"
        local name="${entry##*:}"
        total=$((total + 1))
        
        if compile_practico "$practico" "$name"; then
            success=$((success + 1))
        fi
    done
    
    echo -e "\n${PURPLE}═══════════════════════════════════${NC}"
    echo -e "${CYAN}📊 Resultado: $success/$total prácticos compilados exitosamente${NC}"
    
    if [ $success -eq $total ]; then
        echo -e "${GREEN}🎉 ¡Todos los prácticos compilados correctamente!${NC}"
    fi
}

# Función para ejecutar un práctico
run_practico() {
    local practico=$1
    local class=$2
    local name=$3
    
    echo -e "\n${BLUE}🚀 Ejecutando $name...${NC}"
    echo -e "${BLUE}═══════════════════════════════════════════════════${NC}\n"
    
    # Verificar si está compilado
    if [ ! -d "bin/src/edu/informatica3/lucas_antun/$practico" ]; then
        echo -e "${YELLOW}⚠️ El práctico no está compilado. Compilando ahora...${NC}"
        if ! compile_practico "$practico" "$name"; then
            echo -e "${RED}❌ No se pudo compilar el práctico${NC}"
            return 1
        fi
    fi
    
    # Ejecutar
    java -cp bin src.edu.informatica3.lucas_antun.$practico.$class
}

# Función especial para ejecutar el práctico integrador
run_practico_integrador() {
    echo -e "\n${BLUE}🚀 Ejecutando Sistema de Gestión de Turnos Médicos...${NC}"
    echo -e "${BLUE}═══════════════════════════════════════════════════${NC}\n"
    
    # Verificar si está compilado
    if [ ! -d "src/edu/informatica3/lucas_antun/practico_integrador" ]; then
        echo -e "${RED}❌ El práctico integrador no está disponible${NC}"
        return 1
    fi
    
    # Compilar si es necesario
    echo -e "${YELLOW}🔨 Compilando Sistema de Gestión de Turnos Médicos...${NC}"
    if javac -d /tmp src/edu/informatica3/lucas_antun/practico_integrador/*.java 2>/dev/null; then
        echo -e "${GREEN}✅ Compilación exitosa${NC}\n"
    else
        echo -e "${RED}❌ Error en la compilación${NC}"
        return 1
    fi
    
    # Cambiar al directorio del práctico integrador y ejecutar
    cd src/edu/informatica3/lucas_antun/practico_integrador
    java -cp "$(pwd)/../../../../../src" edu.informatica3.lucas_antun.practico_integrador.SistemaGestionTurnos
    cd - > /dev/null
}

# Función para mostrar ayuda
show_help() {
    clear
    echo -e "${BLUE}╔══════════════════════════════════════════════════════════════╗${NC}"
    echo -e "${BLUE}║${NC}                            ${CYAN}AYUDA${NC}                             ${BLUE}║${NC}"
    echo -e "${BLUE}╚══════════════════════════════════════════════════════════════╝${NC}"
    echo -e ""
    echo -e "${PURPLE}📖 Descripción de los Prácticos:${NC}"
    echo -e ""
    echo -e "${YELLOW}Práctico 1 - Sistema de Gestión de Tareas${NC}"
    echo -e "  • Aplicación para gestionar tareas personales"
    echo -e "  • Funciones: agregar, listar, completar, eliminar tareas"
    echo -e "  • Persistencia en archivos de texto"
    echo -e ""
    echo -e "${YELLOW}Práctico 2 - Ejercicios de Recursividad${NC}"
    echo -e "  • 8 ejercicios de recursividad implementados"
    echo -e "  • Incluye: factorial, fibonacci, palíndromos, MCD, etc."
    echo -e "  • Ejemplos y casos de prueba"
    echo -e ""
    echo -e "${YELLOW}Práctico 3 - Sistema de Pizzería${NC}"
    echo -e "  • Sistema completo de gestión de pedidos"
    echo -e "  • Algoritmos: Insertion Sort, Shell Sort, Quick Sort"
    echo -e "  • Análisis de rendimiento y comparación de algoritmos"
    echo -e ""
    echo -e "${YELLOW}Práctico 4 - Estructuras de Datos${NC}"
    echo -e "  • Implementación de Pilas y Colas genéricas"
    echo -e "  • Aplicaciones prácticas y casos de uso"
    echo -e "  • Análisis de complejidad temporal y espacial"
    echo -e ""
    echo -e "${YELLOW}Práctico 5 - Árboles AVL${NC}"
    echo -e "  • Implementación completa de árboles auto-balanceados"
    echo -e "  • Rotaciones simples y dobles para mantener balance"
    echo -e "  • Análisis de altura y factor de balance"
    echo -e ""
    echo -e "${YELLOW}Práctico 6 - Árboles Rojo-Negro${NC}"
    echo -e "  • Implementación de árboles Red-Black con propiedades completas"
    echo -e "  • Verificación de propiedades y navegación entre nodos"
    echo -e "  • Consultas por rango y análisis de rendimiento"
    echo -e ""
    echo -e "${YELLOW}Práctico Integrador - Sistema de Gestión de Turnos Médicos${NC}"
    echo -e "  • Sistema integral de gestión de turnos médicos"
    echo -e "  • Carga de datos desde archivos CSV (pacientes, médicos, turnos)"
    echo -e "  • Validaciones robustas y reportes detallados"
    echo -e "  • Integra conceptos de POO, estructuras de datos y manejo de archivos"
    echo -e ""
    echo -e "${GREEN}🔧 Requisitos:${NC}"
    echo -e "  • Java JDK 17 o superior"
    echo -e "  • Terminal con soporte para colores (recomendado)"
    echo -e ""
    echo -e "${GREEN}💡 Consejos:${NC}"
    echo -e "  • Use 'c' para compilar todos antes de ejecutar"
    echo -e "  • Los archivos compilados se guardan en la carpeta 'bin/'"
    echo -e "  • Presione Ctrl+C para salir de cualquier programa"
    echo -e ""
}

# Función para pausar y esperar input del usuario
pause() {
    echo -e "\n${CYAN}Presione Enter para continuar...${NC}"
    read
}

# Función principal
main() {
    # Verificar que Java esté instalado
    if ! command -v java &> /dev/null; then
        echo -e "${RED}❌ Error: Java no está instalado o no está en el PATH${NC}"
        echo -e "${YELLOW}💡 Por favor instale Java JDK 17 o superior${NC}"
        exit 1
    fi
    
    # Verificar versión de Java
    java_version=$(java -version 2>&1 | head -n1 | cut -d'"' -f2)
    echo -e "${GREEN}☕ Java detectado: $java_version${NC}\n"
    
    while true; do
        mostrar_menu
        read -r choice
        
        case $choice in
            1)
                run_practico "practico01" "MainTareas" "Sistema de Gestión de Tareas"
                pause
                ;;
            2)
                run_practico "practico02" "EjerciciosRecursividad" "Ejercicios de Recursividad"
                pause
                ;;
            3)
                run_practico "practico03" "MainPizzeria" "Sistema de Pizzería"
                pause
                ;;
            4)
                run_practico "practico04" "DemostracionEstructuras" "Estructuras de Datos (Pilas y Colas)"
                pause
                ;;
            5)
                run_practico "practico05" "DemostracionAVL" "Árboles AVL (Auto-balanceados)"
                pause
                ;;
            6)
                run_practico "practico06" "DemostracionRojoNegro" "Árboles Rojo-Negro (Red-Black Trees)"
                pause
                ;;
            7)
                run_practico_integrador
                pause
                ;;
            c|C)
                compile_all
                pause
                ;;
            h|H)
                show_help
                pause
                ;;
            0|q|Q)
                echo -e "\n${GREEN}👋 ¡Gracias por usar el sistema de prácticos!${NC}"
                echo -e "${CYAN}📚 Desarrollado por Lucas Santiago Said Antun & Federico Fernández${NC}\n"
                exit 0
                ;;
            *)
                echo -e "\n${RED}❌ Opción inválida. Por favor seleccione una opción válida.${NC}"
                sleep 2
                ;;
        esac
    done
}

# Ejecutar función principal
main "$@"