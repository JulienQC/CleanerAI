SRC_PATH=src
CLASS_PATH=build

CLASS=\
	ScreenUpdater\
	Environment\
	Agent\
	Effector\
	Sensor\
	Position\
	State\
	Explorer\
	Heuristic\
	Room\

CLASS_FILES=$(addprefix $(CLASS_PATH)/, $(CLASS:=.class))
SRC=$(addprefix $(SRC_PATH)/, $(CLASS:=.java))

all: pipeline

cc: src
	javac -d $(CLASS_PATH) $(SRC)

run:
	@java -cp $(CLASS_PATH) $(CLASS)

pipeline: cc run

clean:
	@rm -rf $(CLASS_FILES) $(SRC_PATH)/*~
