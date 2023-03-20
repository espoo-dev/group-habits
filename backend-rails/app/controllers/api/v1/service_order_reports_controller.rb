module Api
  module V1
    class ServiceOrderReportsController < ApplicationController
      def show
        service_order = ServiceOrder.first
        respond_to do |format|
          format.pdf do
            pdf = ServiceOrderPdf.new(service_order, view_context)
            send_data pdf.render, filename: "so.pdf", type: "application/pdf"
          end
        end
      end
    end
  end
end
