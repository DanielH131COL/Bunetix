# 🌐 Bunetix

> 🌟 Proxy Core para servidores Minecraft BungeeCord | Totally Free & Open Source!

---

## 📋 Características (Español)

`Bunetix` es un Proxy completo y gratuito para tu servidor de BungeeCord. Diseñado para ser ligero, potente y fácil de configurar, incluye todo lo esencial para un servidor de Minecraft.

### ✅ Funcionalidades principales:

- 🔒 **Sistema de StaffChat**  
  Permite que el staff se comunique de forma privada, ya sea escribiendo directamente o activando el modo automático con `/sc`.

- 🚪 **Mensajes de cambio de servidor**  
  Notifica al staff cuando un jugador cambia de servidor o entra al servidor principal (Hub).

- 📢 **Sistema de Anuncios Automáticos (Announcer)**  
  Anuncios rotativos configurables por servidor y con soporte de intervalos personalizados.

- 📺 **Comando de Stream Personalizado**  
  Los jugadores pueden usar `/stream <link>` para anunciar que están transmitiendo en Twitch, Kick o YouTube. ¡Con colores, íconos y soporte hover!

- ⚙️ **Sistema de Archivos Modular (`FileManager`)**  
  Carga y gestión sencilla de archivos como `config.yml` y `messages.yml`.

- 🎨 **Soporte para gradientes y colorcodes**  
  Usa mensajes coloridos con gradientes, hexadecimales y formatos tradicionales de Minecraft (`&`).

- 🛡️ **Sistema de Permisos Integrado**  
  Compatible con cualquier sistema de permisos, usando `Bunetix.staff`, `Bunetix.staffchat` y `Bunetix.stream`.

---

## 📋 Features (English)

`Bunetix` is a full and free BungeeCord core plugin for Minecraft networks. Lightweight, powerful and easy to use — it includes everything essential for managing your proxy.

### ✅ Key Features:

- 🔒 **StaffChat System**  
  Allows staff members to communicate privately using `/sc` or entering automatic mode.

- 🚪 **Server Switch & Join Alerts**  
  Notifies staff when players switch servers or join the Hub.

- 📢 **Automated Announcer System**  
  Sends rotating announcements per-server with custom intervals and fancy formatting.

- 📺 **Custom Stream Command**  
  Players can use `/stream <link>` to broadcast their Twitch, Kick or YouTube streams — fully configurable with icons and hover text.

- ⚙️ **Modular File System (`FileManager`)**  
  Loads and manages YAML files like `config.yml` and `messages.yml`.

- 🎨 **Gradient & Color Support**  
  Rich chat message formatting using gradients, hex colors and `&` codes.

- 🛡️ **Built-in Permission Checks**  
  Works with any permission plugin using nodes like `Bunetix.staff`, `Bunetix.staffchat` and `Bunetix.stream`.

---

## 📂 Archivos configurables

### `config.yml`

```yaml
servers:
  hubs:
    - "hub1"
    - "hub2"
    - "lobby"

stream:
  cooldown-seconds: 300 # Tiempo en segundos entre usos del comando

  supported-platforms:
    Twitch:
      regex: ".*twitch\\.tv.*"
      icon: "✦"
      color: "&5"
    Kick:
      regex: ".*kick\\.com.*"
      icon: "⚡"
      color: "&a"
    YouTube:
      regex: ".*(youtube\\.com|youtu\\.be).*"
      icon: "▶"
      color: "&c"

announcements:
  tienda:
    interval: 2
    lines:
      - "&a¡Visita nuestra tienda oficial!"
      - "&bhttps://tienda.hylurcraft.art"

  redes:
    interval: 4
    lines:
      - "&aSíguenos en nuestras redes sociales"
      - "&bTwitter y TikTok: @HylurCraft"
```
### `messages.yml`

```yaml
messages:
  ping: "&bPing: &f&l<ping> &bms."

  msg:
    usage: "&cUso: /msg <jugador> <mensaje>"
    not-found: "&cJugador no encontrado."
    to-format: "&a<target>&7: &f<message>"
    from-format: "&a<sender>&7: &f<message>"

  reply:
    usage: "&cUso: /r <mensaje>"
    no-target: "&cNo tienes a nadie a quien responder."
    to-format: "&a<target>&7: &f<message>"
    from-format: "&a<sender>&7: &f<message>"

  hub:
    not-available: "&cNo hay servidores de Hub disponibles en este momento."
    already-there: "&eYa estás en un servidor Hub."
    connecting: "&aConectando a un servidor Hub..."
    redirect-kick: "&cFuiste redirigido a un hub porque el servidor anterior se desconectó."

  staffchat:
    enabled: "&aHas activado el modo StaffChat."
    disabled: "&cHas desactivado el modo StaffChat."
    format: "&7(&e%server%&7) &b%player%&7: &f%message%"
    join-hub: "&8[&a◆&8] &7%player% &eha entrado al servidor."
    switch-server: "&8[&b⇨&8] &7%player% &ese movió al servidor &b(%server%)&e."
    chat-format: "&7(%server%) &e%player%&7: &f%message%"
```