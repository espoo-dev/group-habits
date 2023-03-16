module Api
  module V1
    class ServiceOrderReportsController < ApplicationController
      def show
        respond_to do |format|
          format.html
          format.pdf do
            render pdf: "file_name",
            page_size: 'A4'
          end
        end
      end
    end
  end
end
