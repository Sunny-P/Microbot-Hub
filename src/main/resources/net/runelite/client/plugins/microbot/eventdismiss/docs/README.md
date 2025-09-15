﻿# Event Dismiss Plugin

The **Event Dismiss Plugin** is a utility tool for Old School RuneScape, designed to automatically dismiss random event NPCs that appear during gameplay. Built for the Microbot RuneLite client, this plugin helps keep your gameplay uninterrupted by quickly and efficiently handling unwanted random events.

---

## Features

- **Automatic Random Event Dismissal:**  
  Detects when random event NPCs (such as Genie, Drunken Dwarf, Sandwich Lady, etc.) appear and automatically dismisses them, preventing interruptions during skilling, combat, or other activities.

- **Overlay Display:**  
  Optionally provides a real-time overlay to show when an event has been dismissed or is being handled.

- **Configurable Options:**  
  Users can enable or disable the plugin, and adjust overlay preferences in the configuration panel.

- **Failsafes and Error Handling:**  
  Handles unexpected in-game events and ensures only random event NPCs are dismissed, not important or quest-related NPCs.

---

## How It Works

1. **Configuration:**  
   Enable or disable the plugin and overlay in the configuration panel.

2. **Detection:**  
   The plugin listens for the appearance of random event NPCs.

3. **Automation:**  
   When a random event NPC is detected, the plugin automatically interacts with the NPC to dismiss it.

4. **Overlay (Optional):**  
   Displays a notification or status update when an event is dismissed.

---

## Configuration

The plugin provides a configuration panel (`EventDismissConfig`) where you can:

- Enable or disable the plugin
- Enable or disable overlay notifications

---

## Requirements

- Microbot RuneLite client

---

## Usage

1. **Enable the Plugin:**  
   Open the Microbot sidebar, find the Event Dismiss Plugin, and enable it.

2. **Configure Settings:**  
   Adjust overlay and dismissal preferences as needed.

3. **Play Normally:**  
   The plugin will automatically dismiss random event NPCs as they appear.

---

## Limitations

- Only dismisses random event NPCs defined in the script logic.
- Does not interact with quest or important NPCs.
- May not handle all new or rare random events if not included in the detection list.

---

## Source Files

- `EventDismissPlugin.java` – Main plugin class, manages lifecycle and integration.
- `EventDismissConfig.java` – User configuration options.
- `DismissNpcEvent.java` – Logic for detecting and dismissing random event NPCs.

---

**Keep your gameplay uninterrupted by automatically dismissing random events with the Event Dismiss Plugin!**