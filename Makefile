SRC_PATH=src
CLASS_PATH=build

CLASS=\
	Main\
	ScreenUpdater\
	Environment\
	Agent\
	Effector\
	Sensor\
	Position\
	State\
	Explorer\
	Room\
	House\
	AgentHouse\
	Action\


CLASS_FILES=$(addprefix $(CLASS_PATH)/, $(CLASS:=.class))
SRC=$(addprefix $(SRC_PATH)/, $(CLASS:=.java))
DIRTCD=5 #in seconds
JEWELCD=10 #in seconds
HOUSEX=10
HOUSEY=10
INFORMED=1 #1: informed; 0: non-informed
HOUSE=$(HOUSEX) $(HOUSEY)
ARGS=$(HOUSE) $(DIRTCD) $(JEWELCD) $(INFORMED)

all: pipeline

cc: src
	javac -d $(CLASS_PATH) $(SRC)

run:
	@java -cp $(CLASS_PATH) $(CLASS) $(ARGS)

pipeline: cc run

clean:
	@rm -rf $(CLASS_PATH)/*.class $(SRC_PATH)/*~
