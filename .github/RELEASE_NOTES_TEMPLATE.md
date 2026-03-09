# 📝 Release Notes Template

Use this template when creating a new Release on GitHub.

## Format:

```markdown
- Added new feature X
- Fixed bug Y
- Improved performance Z
```

---

## Guidelines:

1. **Maximum length:** 500 characters (automatically truncated)

2. **Tag name:**
   - `v1.0.0` → production (if normal release)
   - `v1.0.0` → beta (if pre-release)
   - `v1.0.0-alpha` → always alpha
   - `v1.0.0-beta` → always beta
   - `v1.0.0-internal` → always internal

3. **Release type:**
   - ✅ Normal release → usually production
   - ☑️ Pre-release → usually beta/alpha

4. **App version:**
   - Automatically extracted from tag
   - `v1.2.3` → versionName: `1.2.3`
   - `v1.2.3-beta` → versionName: `1.2.3`

## Example:

```markdown
### What's New in v1.0.0

- Added ShareToBypass functionality
- Improved content sharing
- Bug fixes and performance improvements
```

