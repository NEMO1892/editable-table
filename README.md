# EditableTable

App generates a table of a user-defined size, fills it with random data and lets you edit and highlight cells.

---

## Demo

<img src="screenshots/idt.gif" width="300" alt="Demo" />

---

## Features

- **Table size input** - separate fields for rows and columns
  - Validation in the domain layer: 1–1000 rows, 1–6 columns
  - Inline error messages per field
  - Last entered size is stored in DataStore and restored on the next launch
- **Table generation** — random alphanumeric text of 1–16 characters per cell
- **Table screen** — List of cells
  - **Single tap** toggles the cell highlight
  - **Double tap** opens an inline editor stretched over the whole row
  - Editing is closed by the keyboard `Done` action or by tapping another cell
  - Loading indicator while the table is being built