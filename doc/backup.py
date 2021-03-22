import os
import re
import shutil

projects = [
    "https://github.com/hr82al/mu.git",
    "https://github.com/hr82al/mu_trainings.git",
    "https://github.com/hr82al/work_scheduler.git",
    "https://github.com/hr82al/work_plan.git"
]
dest = r"\\10.168.130.9\HMMR_Share\Maintenance_and_Utility_Department\07.USER\U.44.AKH\Work_recording\src"

def bkfile(project):
    file_name = re.match(r".*/(.+?).git", project).group(1)
    shutil.rmtree(file_name, ignore_errors=True)
    os.system(f"git clone {project}")
    shutil.make_archive(file_name, "zip", file_name)
    shutil.rmtree(file_name, ignore_errors=True)
    shutil.move(file_name + ".zip", dest + "\\" + file_name + ".zip")

os.chdir(os.getenv("tmp"))
print(os.path.abspath("."))
for i in projects:
    bkfile(i)


