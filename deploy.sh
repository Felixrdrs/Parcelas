#!/usr/bin/env bash
# deploy.sh — Despliega la última versión de la rama principal
set -euo pipefail
cd "$(dirname "$0")"
echo ">> Descargando cambios del repositorio..."
git pull --ff-only
echo ">> Parando contenedores (los datos de la BD se conservan en el volumen)..."
podman-compose down
echo ">> Construyendo y arrancando la nueva versión..."
podman-compose up -d --build
echo ">> Eliminando imágenes antiguas sin uso..."
podman image prune -f
echo ">> Estado actual:"
podman-compose ps