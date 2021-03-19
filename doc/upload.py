import os
import re
from shutil import copyfile

file_dir = r"\\10.168.150.74\mu\updates"
os.system("mvn package")
files = os.listdir(file_dir)
files = [i for i in files if i.startswith("mu1") and i.endswith(".jar")]
file = files[-1]
oldnum = re.search(r"\d{3}", file).group(0)
newnum = str(int(re.search(r"\d{3}", file).group(0)) + 1).zfill(3)
file = file.replace(oldnum, newnum)
newfile = f"./target/" + file
os.rename(f"./target/mu-1.0-SNAPSHOT.jar", newfile)
copyfile(newfile, file_dir + r"/" + file)



