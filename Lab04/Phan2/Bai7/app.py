import os

app_env = os.environ.get("APP_ENV", "unknown")

print("==================================")
print(f"  APP_ENV = {app_env}")
print("==================================")

if app_env == "development":
    print("  Che do: Phat trien (Development)")
elif app_env == "production":
    print("  Che do: San xuat (Production)")
else:
    print(f"  Che do: {app_env}")

print("==================================")
