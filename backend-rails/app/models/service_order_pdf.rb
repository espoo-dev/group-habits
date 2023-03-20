# :reek:DuplicateMethodCall :reek:TooManyInstanceVariables :reek:TooManyStatements

class ServiceOrderPdf < Prawn::Document
  attr_reader :title_x, :title_y, :title_size, :customer_title_y, :customer_title_x, :customer_title_size,
              :small_margin_left, :medium_margin_left, :large_margin_left,
              :small_margin_top, :medium_margin_top, :large_margin_top,
              :small_font_size, :medium_font_size, :large_font_size,
              :extra_small_space, :small_space, :medium_space, :large_space, :service_order, :customer

  def initialize(service_order, view)
    super()
    @service_order = service_order
    @customer = service_order.customer

    @view = view

    setup_positions

    # stroke_axis
    drawn_title
    drawn_customer_data
  end

  def setup_positions
    @small_margin_left = 20
    @medium_margin_left = 60
    @large_margin_left = 120

    @small_margin_top = 100
    @medium_margin_top = 300
    @large_margin_top = 700

    @extra_small_space = 5
    @small_space = 20
    @medium_space = 40
    @large_space = 60

    @small_font_size = 12
    @medium_font_size = 16
    @large_font_size = 22

    @title_x = 180
    @title_y = large_margin_top
    @title_size = large_font_size

    @customer_title_x = small_margin_left
    @customer_title_y = title_y - medium_space
    @customer_title_size = medium_font_size
  end

  def drawn_title
    draw_text 'Ordem de servico', at: [title_x, title_y], size: title_size
  end

  def drawn_customer_data
    pad_top(large_space) do
      text 'Dados do cliente', size: customer_title_size
    end

    pad_top(extra_small_space) do
      text "#{I18n.t 'activerecord.models.customer.attributes.name'}: #{customer.name}", size: small_font_size
      text "#{I18n.t 'activerecord.models.customer.attributes.document_number'}: #{customer.document_number}",
           size: small_font_size
      customer_type_value_key = "activerecord.models.customer.attributes.customer_type_values.#{customer.customer_type}"
      text "#{I18n.t 'activerecord.models.customer.attributes.customer_type'}: #{I18n.t customer_type_value_key}",
           size: small_font_size
    end

    pad_top(extra_small_space) do
      stroke_horizontal_rule
    end
  end
end
# :reek:DuplicateMethodCall :reek:TooManyInstanceVariables :reek:TooManyStatements
