# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Internet Explorer', exclusive: { platform: :windows } do
  describe 'Options' do
    it 'basic options Win10' do
      options = Selenium::WebDriver::Options.ie
      options.attach_to_edge_chrome = true
      options.edge_executable_path = ENV['EDGE_BINARY']
      @driver = Selenium::WebDriver.for :ie, options: options
    end

    it 'basic options Win11' do
      options = Selenium::WebDriver::Options.ie
      @driver = Selenium::WebDriver.for :ie, options: options
    end
  end

  describe 'Service' do
    let(:file_name) { File.expand_path('iedriver.log') }
    let(:root_directory) { File.expand_path('./root') }

    before { FileUtils.mkdir_p(root_directory) }

    after do
      File.delete(file_name)
      FileUtils.rm_rf(root_directory)
    end

    it 'logs to file' do
      service = Selenium::WebDriver::Service.ie

      service.log = file_name

      @driver = Selenium::WebDriver.for :ie, service: service
      expect(File.readlines(file_name).size).to eq 4
    end

    it 'logs to console' do
      service = Selenium::WebDriver::Service.ie

      service.log = $stdout

      expect {
        @driver = Selenium::WebDriver.for :ie, service: service
      }.to output(/Starting ChromeDriver/).to_stdout_from_any_process
    end

    it 'sets log level' do
      service = Selenium::WebDriver::Service.ie
      service.log = file_name

      service.args << '-log-level=INFO'

      @driver = Selenium::WebDriver.for :ie, service: service
    end

    it 'sets location for supporting files' do
      service = Selenium::WebDriver::Service.ie

      service.args << "–extract-path=#{root_directory}"

      @driver = Selenium::WebDriver.for :ie, service: service
    end
  end
end
