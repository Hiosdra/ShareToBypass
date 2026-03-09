# GitHub Actions - Release to Google Play

This workflow automatically builds and publishes the app to Google Play Console after creating a GitHub Release.

## ✨ How it Works

The workflow runs automatically when you create a new Release on GitHub. It retrieves:
- **App version** from the release tag (e.g., `v1.2.3` → versionName: `1.2.3`)
- **Change description (What's New)** from release notes content
- **Release track** based on the tag and release type (prerelease/normal)

## Required GitHub Secrets

For the workflow to work correctly, you must configure the following secrets in repository settings (Settings → Secrets and variables → Actions):

### 1. `KEYSTORE_BASE64`
Keystore encoded in base64. To obtain it:
```bash
base64 -i your-release-keystore.jks | pbcopy
```
Paste the result into GitHub Secret.

### 2. `KEYSTORE_PASSWORD`
Keystore password.

### 3. `KEY_ALIAS`
Key alias in keystore.

### 4. `KEY_PASSWORD`
Key password.

### 5. `GOOGLE_PLAY_SERVICE_ACCOUNT_JSON`
JSON from Google Play Console Service Account. To obtain it:

1. Go to [Google Play Console](https://play.google.com/console)
2. Select your app
3. Go to **Setup → API access**
4. Create a new Service Account or use an existing one
5. Grant it "Release Manager" or "Admin" permissions
6. Download the JSON key
7. Copy the entire JSON file content and paste it as a GitHub Secret

## How to Use

### Creating a Release (Recommended)

1. **Go to the repository on GitHub**

2. **Click "Releases" → "Create a new release"**

3. **Set the tag:**
   - Format: `v1.0.0`, `v1.0.0-alpha`, `v1.0.0-beta`, `v1.0.0-internal`
   - The tag determines the app version and release track

4. **Write Release notes:**
   
   Simple format (English):
   ```
   - New feature 1
   - Bug fix 2
   - Improved performance
   ```

5. **Choose release type:**
   - ✅ **Normal release** → automatically goes to `production` (or track from tag)
   - ☑️ **Pre-release** → automatically goes to `beta` (or track from tag)

6. **Click "Publish release"** - The workflow will start automatically!

### Automatic track selection:

| Tag | Pre-release | Track |
|-----|-------------|-------|
| `v1.0.0` | ❌ | production |
| `v1.0.0` | ✅ | beta |
| `v1.0.0-alpha` | — | alpha |
| `v1.0.0-beta` | — | beta |
| `v1.0.0-internal` | — | internal |

### Manual Execution (Optional)

1. Go to the **Actions** tab in the repository
2. Select the "Release to Google Play" workflow
3. Click **Run workflow**
4. Provide the release tag and select the track
5. Click **Run workflow**

## What's New Notes

**Release notes are automatically extracted from GitHub Release!**

Simply write your release notes in English:

```markdown
- New feature 1
- Bug fix 2
- Performance improvements
```

The workflow will automatically:
- Extract the content
- Truncate to 500 characters if needed
- Create `whatsnew-en-US` file for Google Play

**Maximum length:** 500 characters (automatically truncated)

## Versioning

- **`versionCode`** - automatically set to the GitHub Actions run number
- **`versionName`** - automatically retrieved from the release tag (e.g., `v1.2.3` → `1.2.3`)

You don't need to manually edit `build.gradle.kts`! The workflow will do it for you.

## Troubleshooting

### "Package not found" Error
Make sure the app has been manually uploaded to Google Play Console at least once.

### Signing Error
Check that all secrets (KEYSTORE_*, KEY_*) are correctly set.

### Service Account Error
Make sure the Service Account has appropriate permissions in Google Play Console.

## 🚀 Example: First Release

### Step by Step:

1. **Commit and push changes:**
   ```bash
   git add .
   git commit -m "Preparation for version 1.0.0"
   git push origin main
   ```

2. **Go to GitHub → Releases → Create a new release**

3. **Fill out the form:**
   - **Choose a tag:** `v1.0.0` (create new tag)
   - **Release title:** `Version 1.0.0` (optional)
   - **Describe this release:**
     ```markdown
     - First version of the app
     - Basic ShareToBypass functionality
     - Content sharing support
     ```
   - **Pre-release:** ❌ (leave unchecked for production)

4. **Click "Publish release"**

5. **Check status:**
   - Go to the **Actions** tab
   - Wait for the workflow to complete (about 5-10 minutes)
   - Check Google Play Console to see if the app appeared

### Next Versions:

For beta/alpha versions:
```bash
# Tag: v1.1.0-beta
# Pre-release: ✅ or use tag with -beta/-alpha
```

For production versions:
```bash
# Tag: v1.1.0
# Pre-release: ❌
```

## 📖 Additional Resources

- [GitHub Releases Documentation](https://docs.github.com/en/repositories/releasing-projects-on-github/about-releases)
- [Google Play Console Help](https://support.google.com/googleplay/android-developer/)
