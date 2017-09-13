# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  config.ssh.forward_agent = true # So that boxes don't have to setup key-less ssh
  config.ssh.insert_key = false # To generate a new ssh key and don't use the default Vagrant one
  config.ssh.shell = "bash -c 'BASH_ENV=/etc/profile exec bash'"

  config.vm.provider "virtualbox" do |v|
    v.memory = 4096
    v.cpus = 2
  end

  config.vm.define "one" do |s|
    s.vm.box = "ubuntu/trusty64"
    s.vm.network "private_network", type: "dhcp"
    s.vm.hostname = "one"
    s.vm.provision "shell", inline:
      "sudo apt-get update;"\
      "sudo apt-get -y install default-jre;"\
      "sudo apt-get -y install default-jdk;"\
      "sudo add-apt-repository 'deb [arch=amd64] http://packages.confluent.io/deb/3.3 stable main';"\
      "sudo apt-get update;"\
      "wget http://mirror.metrocast.net/apache/kafka/0.11.0.0/kafka_2.11-0.11.0.0.tgz;"\
      "tar -xf kafka_2.11-0.11.0.0.tgz;"\
      "echo w00t!"
  end

end
