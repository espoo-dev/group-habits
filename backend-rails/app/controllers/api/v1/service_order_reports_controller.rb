module Api
  module V1
    class ServiceOrderReportsController < ApplicationController
      before_action :set_locale

      def show
        # service_order = ServiceOrders::ServiceOrderFindService.new(user: User.first, params: show_params).call
        service_order = ServiceOrders::ServiceOrderFindService.new(user: current_user, params: show_params).call
        respond_to do |format|
          format.pdf do
            pdf = ServiceOrderPdf.new(service_order, view_context)
            send_data pdf.render, filename: 'so.pdf', type: 'application/pdf'
          end
        end
      end

      private

      def set_locale
        I18n.locale = 'pt-br'
      end

      def show_params
        params.permit(:id)
      end
    end
  end
end
