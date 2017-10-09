SRC_PATH=src
CLASS_PATH=build

CLASS=ScreenUpdater\

SRC=$(addprefix $(SRC_PATH)/, $(CLASS:=.java))

all: cc

cc: src
	javac -d $(CLASS_PATH) $(SRC)

run:
	@java -cp $(CLASS_PATH) $(CLASS) 

clean:
	@rm -rf $(CLASS) *~
