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
    s.vm.network "private_network", ip: "192.168.99.99"
    s.vm.hostname = "one"
    s.vm.provision "ansible" do |ansible|
      ansible.playbook = "provision.yml"
    end

  end

end
