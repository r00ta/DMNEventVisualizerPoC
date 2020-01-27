# DMNEventVisualizerPoC
PoC DMN Event visualizer

Build and run the containers with 
```bash
sudo docker-compose build && sudo docker-compose up
```

The architecture is more or less like this 

![TrustyAIArch](https://user-images.githubusercontent.com/18282531/73201355-28b34500-4139-11ea-9561-4bf9e049bacf.png)

1) A (mocked) producer will generate events (DMN Model register, DMN decisions) and send them to kafka
2) A consumer (trusty service) will get the decisions and display them
